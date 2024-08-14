package com.africa.semicolon.data.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class BuyBookResponse {
    @Id
    private String id;
    private String title;
    private String author;
    private String message;
}
