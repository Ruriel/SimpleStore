package com.ruriel.simplestore.api.v1.resources;

import com.ruriel.simplestore.entities.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    private Long id;

    private BigDecimal total;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Set<PurchaseItemResponse> items;
}
