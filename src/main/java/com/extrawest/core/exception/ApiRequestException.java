package com.extrawest.core.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(ExceptionMessage message) {
        super(message.getExMessage());
    }

    public ApiRequestException(ExceptionMessage message, Long id) {
        super(message.getExMessage());
    }

    public ApiRequestException(ExceptionMessage message, String email) {
        super(message.getExMessage());
    }

    public ApiRequestException(String message) {
        super(message);
    }
}
