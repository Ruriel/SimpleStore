package com.ruriel.simplestore.services;

import com.ruriel.simplestore.api.exceptions.InvalidProductBuyingException;
import com.ruriel.simplestore.api.exceptions.InvalidStatusChangeException;
import com.ruriel.simplestore.api.exceptions.ResourceNotFoundException;
import com.ruriel.simplestore.entities.Purchase;
import com.ruriel.simplestore.entities.PurchaseItem;
import com.ruriel.simplestore.entities.Product;
import com.ruriel.simplestore.entities.Status;
import com.ruriel.simplestore.repositories.PurchaseRepository;
import com.ruriel.simplestore.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTests {
    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    void shouldFindPage(){
        var pageable = PageRequest.of(0, 5);
        purchaseService.findPage(pageable);
        verify(purchaseRepository).findAll(pageable);
    }

    @Test
    void shouldCreate(){
        var id = 1L;
        var product = Product.builder().id(id).name("Testing").description("Testing").quantity(1L).price(BigDecimal.TEN).build();
        var item = PurchaseItem.builder().product(product).quantity(1L).build();
        var order = Purchase.builder().items(Set.of(item)).build();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        purchaseService.create(order);
        verify(purchaseRepository).save(order);
    }

    @Test
    void shouldThrowInvalidProductBuyingException(){
        var id = 1L;
        var product = Product.builder().id(id).name("Testing").description("Testing").quantity(1L).price(BigDecimal.TEN).build();
        var item = PurchaseItem.builder().product(product).quantity(2L).build();
        var order = Purchase.builder().items(Set.of(item)).build();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        assertThatThrownBy(() -> purchaseService.create(order)).isInstanceOf(InvalidProductBuyingException.class);
    }
    @Test
    void shouldFindById(){
        var id = 1L;
        var product = Product.builder().name("Testing").description("Testing").quantity(1L).price(BigDecimal.TEN).build();
        var item = PurchaseItem.builder().product(product).quantity(1L).build();
        var order = Purchase.builder().items(Set.of(item)).build();
        when(purchaseRepository.findById(id)).thenReturn(Optional.of(order));
        purchaseService.findById(id);
        verify(purchaseRepository).findById(id);
    }

    @Test
    void shouldNotFindById(){
        var id = 1L;
        assertThatThrownBy(() -> purchaseService.findById(id))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldPatch(){
        var id = 1L;
        var product = Product.builder().name("Testing").description("Testing").quantity(1L).price(BigDecimal.TEN).build();
        var item = PurchaseItem.builder().product(product).quantity(1L).build();
        var currentOrder = Purchase.builder().items(Set.of(item)).status(Status.PENDING).build();
        var order = Purchase.builder().status(Status.PROCESSING).build();
        when(purchaseRepository.findById(id)).thenReturn(Optional.of(currentOrder));
        purchaseService.patch(id, order);
        verify(purchaseRepository).save(currentOrder);
    }
}
