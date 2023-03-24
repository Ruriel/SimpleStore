package com.ruriel.simplestore.services.rabbitmq.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruriel.simplestore.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchasePayload implements Serializable {
    @JsonProperty
    private Long id;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private Status status;
}
