package com.ruriel.simplestore.api.v1.controllers;

import com.ruriel.simplestore.api.v1.resources.*;
import com.ruriel.simplestore.entities.Purchase;
import com.ruriel.simplestore.services.PurchaseService;
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
@RequestMapping(value = "/purchase", produces = "application/vnd.assembly.api.v1+json")
@RequiredArgsConstructor
@Slf4j
public class PurchaseController {
    private final ModelMapper modelMapper;

    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<PurchaseResponse>> findPage(@PageableDefault(sort = {"createdAt"}) Pageable pageable) {
        log.trace("GET /purchase");
        log.trace(pageable.toString());
        var page = purchaseService.findPage(pageable);
        var typeToken = new TypeToken<PaginatedResponse<PurchaseResponse>>() {
        }.getType();
        PaginatedResponse<PurchaseResponse> purchasePaginatedResponse = modelMapper.map(page, typeToken);
        return ResponseEntity.ok(purchasePaginatedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> findById(@PathVariable Long id) {
        log.trace("GET /purchase/" + id);
        var purchase = purchaseService.findById(id);
        var purchaseResponse = modelMapper.map(purchase, PurchaseResponse.class);
        return ResponseEntity.ok(purchaseResponse);
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> create(@RequestBody @Valid NewPurchaseRequest purchaseRequest) {
        log.trace("POST /purchase: "+ purchaseRequest.toString());
        var purchase = modelMapper.map(purchaseRequest, Purchase.class);
        var savedPurchase = purchaseService.create(purchase);
        var responseBody = modelMapper.map(savedPurchase, PurchaseResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PurchaseResponse> patch(@PathVariable Long id, @RequestBody @Valid PatchPurchaseRequest purchaseRequest){
        log.trace(String.format("PATCH /purchase/%d: %s", id, purchaseRequest.toString()));
        var purchase = modelMapper.map(purchaseRequest, Purchase.class);
        var patchedPurchased = purchaseService.patch(id, purchase);
        var responseBody = modelMapper.map(patchedPurchased, PurchaseResponse.class);
        return  ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
