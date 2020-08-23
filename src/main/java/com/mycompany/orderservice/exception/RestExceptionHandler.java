package com.mycompany.orderservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceRuntimeException.class)
    protected ResponseEntity<Object> applicationException(ServiceRuntimeException ex) {

        ApiError apiError;
        String errorCode = ex.getErrorCode();

        switch (errorCode) {
            case ErrorCode.NOT_FOUND:
                apiError = new ApiError(HttpStatus.NOT_FOUND);
                apiError.setMessage(ex.getMessage());
                break;
            case ErrorCode.CAN_NOT_CREATE:
                apiError = new ApiError(HttpStatus.BAD_REQUEST);
                apiError.setMessage(ex.getMessage());
                break;
            case ErrorCode.SERVICE_ERROR:
                apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE);
                apiError.setMessage(ex.getMessage());
                break;
            default:
                apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), ex);
                break;
        }
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
