package com.africa.semicolon.data.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowBookRequest {
    private String email;
    private String title;
    private boolean isBorrowed;
}
