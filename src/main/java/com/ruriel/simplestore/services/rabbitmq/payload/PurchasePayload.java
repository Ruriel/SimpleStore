package com.ruriel.simplestore.services.rabbitmq.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruriel.simplestore.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchasePayload implements Serializable {
    @JsonProperty
    private Long id;

    @JsonProperty
    private LocalDateTime createdAt;

    @JsonProperty
    private LocalDateTime updatedAt;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private Status status;
}
