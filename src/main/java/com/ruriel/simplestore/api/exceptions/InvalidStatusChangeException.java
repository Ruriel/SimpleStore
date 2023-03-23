package com.ruriel.simplestore.api.exceptions;

import com.ruriel.simplestore.entities.Status;

public class InvalidStatusChangeException extends BadRequestException{

    public InvalidStatusChangeException(Status oldStatus, Status newStatus) {
        super(String.format("Cant't update status from %s to %s.", oldStatus, newStatus));
    }
}
