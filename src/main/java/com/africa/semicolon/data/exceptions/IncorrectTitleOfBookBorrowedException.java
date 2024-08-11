package com.africa.semicolon.data.exceptions;

public class IncorrectTitleOfBookBorrowedException extends RuntimeException{
    public IncorrectTitleOfBookBorrowedException(String message){
        super(message);
    }
}
