package com.example.cinemate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
