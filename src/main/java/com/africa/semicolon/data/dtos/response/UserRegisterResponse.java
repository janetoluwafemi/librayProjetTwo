package com.africa.semicolon.data.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRegisterResponse {
    @Id
    private String id;
    private String message;
}
