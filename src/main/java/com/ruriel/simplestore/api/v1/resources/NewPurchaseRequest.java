package com.ruriel.simplestore.api.v1.resources;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPurchaseRequest {
    @NotNull(message = "Must have at least one item")
    private Set<PurchaseItemRequest> items;
}
