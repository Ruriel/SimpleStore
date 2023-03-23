package com.ruriel.simplestore.controllers;

import com.ruriel.simplestore.api.v1.controllers.ProductController;
import com.ruriel.simplestore.api.v1.resources.ProductRequest;
import com.ruriel.simplestore.entities.Product;
import com.ruriel.simplestore.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTests {
    @Mock
    private ProductService productService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductController productController;

    @Test
    void shouldGetAPage() {
        var pageable = PageRequest.of(0, 5);
        productController.findPage(pageable);
        verify(productService).findPage(pageable);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        productController.findById(id);
        verify(productService).findById(id);
    }

    @Test
    void shouldCreate() {
        var id = 1L;
        var product = Product.builder().id(id).build();
        var productRequest = new ProductRequest();
        when(modelMapper.map(productRequest, Product.class)).thenReturn(product);
        productController.create(productRequest);
        verify(productService).create(product);
    }

    @Test
    void shouldUpdate() {
        var id = 1L;
        var product = Product.builder().id(id).build();
        var productRequest = new ProductRequest();
        when(modelMapper.map(productRequest, Product.class)).thenReturn(product);
        productController.update(id, productRequest);
        verify(productService).update(id, product);
    }

    @Test
    void shouldDisable() {
        var id = 1L;
        productController.delete(id);
        verify(productService).disable(id);
    }
}
