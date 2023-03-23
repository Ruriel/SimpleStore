package com.ruriel.simplestore.api.exceptions;

public class StatusAreTheSameException extends BadRequestException{
    public StatusAreTheSameException() {
        super("Statuses are the same.");
    }
}
