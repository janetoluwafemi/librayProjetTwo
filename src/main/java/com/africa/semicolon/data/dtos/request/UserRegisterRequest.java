package com.africa.semicolon.data.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRegisterRequest {
    @Id
    private String id;
    private String name;
    private String email;
    private String emailPassword;
    private String phoneNumber;
}
