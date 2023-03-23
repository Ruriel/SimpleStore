package com.ruriel.simplestore.api.v1.resources;

import com.ruriel.simplestore.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatchPurchaseRequest {
    @Enumerated(EnumType.STRING)
    private Status status;
}
