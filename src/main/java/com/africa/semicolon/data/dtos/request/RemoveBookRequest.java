package com.africa.semicolon.data.dtos.request;

import lombok.Data;

@Data
public class RemoveBookRequest {
    private String title;
    private String email;
    private String author;
}
