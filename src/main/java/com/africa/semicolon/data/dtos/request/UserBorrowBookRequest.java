package com.africa.semicolon.data.dtos.request;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBorrowBookRequest {
    private String email;
    private String name;
    private String emailPassword;
    private String bookTitle;
    private String id;
}
