package com.africa.semicolon.data.dtos.request;

import lombok.Data;

@Data
public class UserLogOutRequest {
    private String email;
    private String message;
}
