package com.africa.semicolon.data.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class IssueBookRequest {
    @Id
    private String id;
    private String email;
    private String title;
    private String bookAuthor;
}
