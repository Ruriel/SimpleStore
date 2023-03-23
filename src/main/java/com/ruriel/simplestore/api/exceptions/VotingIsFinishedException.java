package com.ruriel.simplestore.api.exceptions;


public class VotingIsFinishedException extends BadRequestException{
    public VotingIsFinishedException(String message) {
        super(message);
    }
}
