package com.ruriel.simplestore.controllers;

import com.ruriel.simplestore.api.v1.controllers.PurchaseController;
import com.ruriel.simplestore.api.v1.resources.NewPurchaseRequest;
import com.ruriel.simplestore.api.v1.resources.PatchPurchaseRequest;
import com.ruriel.simplestore.entities.Purchase;
import com.ruriel.simplestore.entities.PurchaseItem;
import com.ruriel.simplestore.entities.Product;
import com.ruriel.simplestore.entities.Status;
import com.ruriel.simplestore.services.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseControllerTests {
    @Mock
    private PurchaseService purchaseService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PurchaseController purchaseController;

    @Test
    void shouldGetAPage() {
        var pageable = PageRequest.of(0, 5);
        purchaseController.findPage(pageable);
        verify(purchaseService).findPage(pageable);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        purchaseController.findById(id);
        verify(purchaseService).findById(id);
    }

    @Test
    void shouldCreate() {
        var id = 1L;
        var product = Product.builder().id(id).name("Testing").description("Testing").quantity(1L).price(BigDecimal.TEN).build();
        var item = PurchaseItem.builder().product(product).quantity(1L).build();
        var purchase = Purchase.builder().items(Set.of(item)).build();
        var purchaseRequest = new NewPurchaseRequest();
        when(modelMapper.map(purchaseRequest, Purchase.class)).thenReturn(purchase);
        purchaseController.create(purchaseRequest);
        verify(purchaseService).create(purchase);
    }

    @Test
    void shouldPatch() {
        var id = 1L;
        var purchase = Purchase.builder().status(Status.PROCESSING).build();
        var purchaseRequest = new PatchPurchaseRequest();
        when(modelMapper.map(purchaseRequest, Purchase.class)).thenReturn(purchase);
        purchaseController.patch(id, purchaseRequest);
        verify(purchaseService).patch(id, purchase);
    }

}
