package com.example.cinemate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminLoginRequestDto {
    private String email;
    private String password;
}
