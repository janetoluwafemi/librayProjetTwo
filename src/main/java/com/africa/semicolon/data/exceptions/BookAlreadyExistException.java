package com.africa.semicolon.data.exceptions;

public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String message){
        super(message);
    }
}
