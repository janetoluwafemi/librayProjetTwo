package com.africa.semicolon.data.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserReturnBookResponse {
    private String email;
    private String name;
    private String emailPassword;
    private String bookTitle;
    private boolean isBorrowed;
    private String message;
    @Id
    private String id;
}
