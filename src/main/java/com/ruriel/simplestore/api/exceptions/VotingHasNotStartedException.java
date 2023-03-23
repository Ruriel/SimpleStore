package com.ruriel.simplestore.api.exceptions;


public class VotingHasNotStartedException extends BadRequestException{
    public VotingHasNotStartedException(String message) {
        super(message);
    }
}
