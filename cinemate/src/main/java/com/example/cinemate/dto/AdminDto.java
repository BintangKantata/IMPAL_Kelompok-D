package com.example.cinemate.dto;

import lombok.Data;

@Data
public class AdminDto {
    private Long id;
    private String username;
    private String email;

     // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}