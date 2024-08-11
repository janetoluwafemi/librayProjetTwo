package com.africa.semicolon.data.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class LoginRequest {
    @Id
    private String id;
    private String email;
    private String emailPassword;
    private String name;
    private boolean isRegistered;
}
