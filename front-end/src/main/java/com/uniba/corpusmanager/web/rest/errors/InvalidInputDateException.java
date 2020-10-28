package com.uniba.corpusmanager.web.rest.errors;

public class InvalidInputDateException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public InvalidInputDateException() {
        super("Invalid input dates", "operationRequest", "invalidDate");
    }
}
