package com.ruriel.simplestore.api.exceptions;

public class VotingHasAlreadyStartedException extends BadRequestException{
    public VotingHasAlreadyStartedException(String message) {
        super(message);
    }
}
