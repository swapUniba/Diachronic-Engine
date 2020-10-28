package com.uniba.corpusmanager.web.rest.errors;

public class UnauthorizedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super("Permission denied", "operationRequest", "invalidCredentials");
    }
}
