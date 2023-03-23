package com.ruriel.simplestore.api.v1.controllers;

import com.ruriel.simplestore.api.v1.resources.*;
import com.ruriel.simplestore.entities.Product;
import com.ruriel.simplestore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product", produces = "application/vnd.simplestore.api.v1+json")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<PaginatedResponse<ProductResponse>> findPage(@PageableDefault(sort = {"createdAt"}) Pageable pageable) {
        log.trace("GET /product");
        log.trace(pageable.toString());
        var page = productService.findPage(pageable);
        var typeToken = new TypeToken<PaginatedResponse<ProductResponse>>() {
        }.getType();
        PaginatedResponse<ProductResponse> productPaginatedResponse = modelMapper.map(page, typeToken);
        return ResponseEntity.ok(productPaginatedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        log.trace("GET /product/" + id);
        var product = productService.findById(id);
        var productResponse = modelMapper.map(product, ProductResponse.class);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest productRequest) {
        log.trace("POST /product: "+ productRequest.toString());
        var product = modelMapper.map(productRequest, Product.class);
        var savedProduct = productService.create(product);
        var responseBody = modelMapper.map(savedProduct, ProductResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        log.trace(String.format("PUT /product/%d: %s", id, productRequest.toString()));
        var product = modelMapper.map(productRequest, Product.class);
        var updatedProduct = productService.update(id, product);
        var responseBody = modelMapper.map(updatedProduct, ProductResponse.class);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable Long id) {
        log.trace(String.format("DELETE /product/%d", id));
        var disable = productService.disable(id);
        var responseBody = modelMapper.map(disable, ProductResponse.class);
        return ResponseEntity.ok(responseBody);
    }
}
