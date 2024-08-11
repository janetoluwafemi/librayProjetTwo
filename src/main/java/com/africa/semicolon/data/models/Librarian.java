package com.africa.semicolon.data.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Librarian {
    private String name;
    private String email;
    private String title;
    private List<Book>bookList = new ArrayList<>();
}
