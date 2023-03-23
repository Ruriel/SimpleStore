package com.ruriel.simplestore.api.exceptions;


public class AssociateNotRegisteredInAgendaException extends ResourceNotFoundException{
    public AssociateNotRegisteredInAgendaException(String message) {
        super(message);
    }
}
