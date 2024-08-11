package com.africa.semicolon.data.dtos.request;

import lombok.Data;

@Data
public class VerifyMemberRequest {
    private String email;
    private String password;
}
