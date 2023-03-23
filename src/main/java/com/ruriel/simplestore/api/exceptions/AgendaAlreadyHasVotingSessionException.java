package com.ruriel.simplestore.api.exceptions;


public class AgendaAlreadyHasVotingSessionException extends BadRequestException {
    public AgendaAlreadyHasVotingSessionException(String message) {
        super(message);
    }
}
