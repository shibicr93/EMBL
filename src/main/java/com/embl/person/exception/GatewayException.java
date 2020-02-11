package com.embl.person.exception;

public class GatewayException extends RuntimeException {
    private static final long serialVersionUID = 7467733049158460187L;

    public static final String GATEWAY_ERROR_KEY_DEFAULT = "GATEWAY_ERROR";

    protected String errorKey;

    public GatewayException(Throwable cause) {
        this(GATEWAY_ERROR_KEY_DEFAULT, cause);
    }

    public GatewayException(String errorKey, Throwable cause) {
        super(errorKey, cause);
        this.errorKey = errorKey;
    }


    public String getErrorKey() {
        return errorKey;
    }
}
