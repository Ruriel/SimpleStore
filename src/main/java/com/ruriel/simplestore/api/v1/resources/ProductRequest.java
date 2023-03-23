package com.ruriel.simplestore.api.v1.resources;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Name should not be null or blank.")
    @Size(max = 30, message = "Name must have a maximum of 30 characters.")
    private String name;

    @NotBlank(message = "Description should not be null or blank.")
    @Size(max = 160, message = "Description must have a maximum of 160 characters.")
    private String description;

    @NotNull(message = "Quantity must not be null")
    @Positive(message = "At least one product must be registered")
    private Long quantity;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    @Digits(fraction = 2, integer = 5, message = "Price should have a integer part of 5 digits maximum " +
            "and the fractional part should not exceed 2")
    private BigDecimal price;

}
