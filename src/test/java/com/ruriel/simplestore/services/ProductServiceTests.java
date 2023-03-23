package com.ruriel.simplestore.services;

import com.ruriel.simplestore.api.exceptions.ResourceNotFoundException;
import com.ruriel.simplestore.entities.Product;
import com.ruriel.simplestore.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void shouldFindPage(){
        var pageable = PageRequest.of(0, 5);
        productService.findPage(pageable);
        verify(productRepository).findByEnabled(true, pageable);
    }

    @Test
    void shouldCreate(){
        var product = Product.builder().name("Testing").description("Testing").build();
        productService.create(product);
        verify(productRepository).save(product);
    }

    @Test
    void shouldFindById(){
        var id = 1L;
        var product = Product.builder().id(id).build();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        productService.findById(id);
        verify(productRepository).findById(id);
    }

    @Test
    void shouldNotFindById(){
        var id = 1L;
        assertThatThrownBy(() -> productService.findById(id))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldUpdate(){
        var id = 1L;
        var currentProduct = Product.builder().id(id).build();
        var product = Product.builder().name("New name").build();
        when(productRepository.findById(id)).thenReturn(Optional.of(currentProduct));
        productService.update(1L, product);
        verify(productRepository).save(currentProduct);
    }

    @Test
    void shouldDisable(){
        var id = 1L;
        var currentProduct = Product.builder().id(id).enabled(true).build();
        when(productRepository.findById(id)).thenReturn(Optional.of(currentProduct));
        productService.disable(1L);
        verify(productRepository).save(currentProduct);
    }
}
