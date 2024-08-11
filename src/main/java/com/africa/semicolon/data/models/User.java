package com.africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String emailPassword;
    private String phoneNumber;
    private boolean isLogIn;
    private boolean isRegistered;
    private boolean isBorrowed;
    private boolean isReturned;
    private List<Book> bookList = new ArrayList<>();
    private String bookTitle;
}




