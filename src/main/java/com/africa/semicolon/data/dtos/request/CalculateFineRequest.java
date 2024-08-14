package com.africa.semicolon.data.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CalculateFineRequest {
    @Id
    private String userId;
    private String  price;
    private String title;
}
