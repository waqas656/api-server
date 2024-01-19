package com.whatsapp.apiserver.exceptions;

public class IllegalMessageTypeException extends RuntimeException {
    public IllegalMessageTypeException(String message) {
        super(message);
    }
}
