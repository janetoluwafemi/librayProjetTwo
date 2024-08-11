package com.africa.semicolon.data.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String string) {
        super(string);
    }
}
