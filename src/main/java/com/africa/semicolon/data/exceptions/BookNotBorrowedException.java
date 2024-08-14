package com.africa.semicolon.data.exceptions;

public class BookNotBorrowedException extends RuntimeException{
    public BookNotBorrowedException(String message){
        super(message);
    }
}
