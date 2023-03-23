package com.ruriel.simplestore.api.v1.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemRequest {
    @NotNull(message = "Quantity must not be null")
    @Positive(message = "At least one product must be registered")
    private Long quantity;

    @NotNull(message = "ProductId must not be null")
    private Long productId;

}
