package com.africa.semicolon.data.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class IssueBookResponse {
    private String title;
    private String message;
    @Id
    private String id;
}
