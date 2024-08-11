package com.africa.semicolon.data.dtos.request;

import lombok.Data;

@Data
public class AddBookRequest {
    private String title;
    private String content;
    private String Author;
    private String email;
}
