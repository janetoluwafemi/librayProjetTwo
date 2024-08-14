package com.africa.semicolon.data.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyBookRequest {
    private String email;
    private String author;
    private String title;
    private boolean isBorrowed;
}
