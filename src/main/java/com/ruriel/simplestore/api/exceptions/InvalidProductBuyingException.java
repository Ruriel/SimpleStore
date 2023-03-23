package com.ruriel.simplestore.api.exceptions;

import com.ruriel.simplestore.entities.Product;

public class InvalidProductBuyingException extends BadRequestException{
    public InvalidProductBuyingException(Product product, Long quantity) {
        super(String.format("Product with id %d only has %d units. Asked for %d.",
                product.getId(), product.getQuantity(), quantity));
    }
}
