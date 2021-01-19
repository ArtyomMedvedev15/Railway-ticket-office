package com.railwayticket.service.exception;

public class ClientServiceException extends ServiceException {
    public ClientServiceException(String message) {
        super(message);
    }

    public ClientServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
