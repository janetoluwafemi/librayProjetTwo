package com.africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("liberian")
public class Liberian {
    private String name;
    @Id
    private String id;
    private String email;
    private String title;
    private String bookAuthor;
    private String price;
    @DBRef
    private List<Book> listOfbooks = new ArrayList<>();
    @DBRef
    private List<User> listOfUsers = new ArrayList<>();
}
