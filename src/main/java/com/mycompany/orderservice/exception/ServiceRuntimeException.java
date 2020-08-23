package com.mycompany.orderservice.exception;

public class ServiceRuntimeException extends RuntimeException{

    private String errorCode;

    public ServiceRuntimeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceRuntimeException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
