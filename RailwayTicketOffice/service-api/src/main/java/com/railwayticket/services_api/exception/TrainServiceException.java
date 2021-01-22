package com.railwayticket.services_api.exception;

public class TrainServiceException extends ServiceException {
    public TrainServiceException(String message) {
        super(message);
    }

    public TrainServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainServiceException() {
    }
}
