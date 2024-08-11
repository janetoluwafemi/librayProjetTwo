package com.africa.semicolon.data.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class UserBorrowBookResponse {
    private boolean isBorrowed;
    private String message;
    private LocalDateTime timeOfBookBorrowed;
}
