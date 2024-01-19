package com.whatsapp.apiserver.exceptions;

public class ChatroomNotFoundException extends RuntimeException {
    public ChatroomNotFoundException(String message) {
        super(message);
    }
}