package com.ruriel.simplestore.services;

import com.ruriel.simplestore.api.exceptions.*;
import com.ruriel.simplestore.entities.Purchase;
import com.ruriel.simplestore.entities.PurchaseItem;
import com.ruriel.simplestore.entities.Status;
import com.ruriel.simplestore.repositories.PurchaseRepository;
import com.ruriel.simplestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    private final ProductRepository productRepository;

    public Page<Purchase> findPage(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No order found"));
    }

    private void validateItem(PurchaseItem item) throws ResourceNotFoundException, InvalidProductBuyingException {
        var productId = item.getProduct().getId();
        var product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product with id %d not found", productId)
                )
        );
        if (product.getQuantity() >= item.getQuantity()) {
            var quantity = product.getQuantity() - item.getQuantity();
            var now = LocalDateTime.now();
            product.setQuantity(quantity);
            product.setUpdatedAt(now);
            item.setProduct(product);
            item.setCreatedAt(now);
        } else {
            throw new InvalidProductBuyingException(product, item.getQuantity());
        }
    }

    public void validateStatus(Long id,  Status newStatus) {
        var foundPurchase = this.findById(id);
        var oldStatus = foundPurchase.getStatus();
        if (oldStatus.equals(newStatus))
            throw new StatusAreTheSameException();
        if (oldStatus.equals(Status.PENDING) &&
                Objects.equals(Status.FINISHED, newStatus)
                ||
                oldStatus.equals(Status.PROCESSING) &&
                        Objects.equals(Status.PENDING, newStatus)
                ||
                oldStatus.equals(Status.FINISHED) &&
                        Arrays.asList(Status.PENDING, Status.PROCESSING, Status.CANCELED).contains(newStatus)
                ||
                oldStatus.equals(Status.CANCELED)
                        && Arrays.asList(Status.PENDING, Status.PROCESSING, Status.FINISHED).contains(newStatus)
        )
            throw new InvalidStatusChangeException(oldStatus, newStatus);
    }

    public Purchase create(Purchase purchase) {
        var now = LocalDateTime.now();
        purchase.getItems().forEach(item -> {
            validateItem(item);
            item.setPurchase(purchase);
        });
        var total = purchase.getItems().stream().map(PurchaseItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        purchase.setCreatedAt(now);
        purchase.setTotal(total);
        purchase.setStatus(Status.PENDING);
        return purchaseRepository.save(purchase);
    }

    public Purchase patch(Long id, Purchase purchase) {
        var foundPurchase = this.findById(id);
        if(purchase.getStatus() != null) {
            foundPurchase.setStatus(purchase.getStatus());
            foundPurchase.setUpdatedAt(LocalDateTime.now());
        }
        return purchaseRepository.save(foundPurchase);
    }
}
