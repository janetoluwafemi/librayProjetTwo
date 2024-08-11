package com.africa.semicolon.data.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserReturnBookRequest {
    private String email;
    private String name;
    private String emailPassword;
    private String bookTitle;
    private boolean isBorrowed;
    @Id
    private String id;
}
