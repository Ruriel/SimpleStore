package com.ruriel.simplestore.services;

import com.ruriel.simplestore.api.exceptions.ResourceNotFoundException;
import com.ruriel.simplestore.entities.Product;
import com.ruriel.simplestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findPage(Pageable pageable){
        return productRepository.findByEnabled(true, pageable);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No product found"));
    }

    public Product create(Product product){
        product.setCreatedAt(LocalDateTime.now());
        product.setEnabled(true);
        return productRepository.save(product);
    }

    public Product update(Long id, Product product){
        var foundProduct = findById(id);
        foundProduct.setName(product.getName());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setQuantity(product.getQuantity());
        foundProduct.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(foundProduct);
    }

    public Product disable(Long id){
        var product = findById(id);
        product.setEnabled(false);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }
}
