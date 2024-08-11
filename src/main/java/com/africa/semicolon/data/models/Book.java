package com.africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private boolean isBorrowed = false;
}
