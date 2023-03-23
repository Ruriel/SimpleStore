package com.ruriel.simplestore.api.v1.resources;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemResponse {

    private Long quantity;

    private Long productId;

}
