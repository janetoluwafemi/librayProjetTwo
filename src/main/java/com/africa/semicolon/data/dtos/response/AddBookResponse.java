package com.africa.semicolon.data.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class AddBookResponse {
    @Id
    private String id;
    private String title;
    private String message;
    private LocalDateTime timeBorrowed;
}
