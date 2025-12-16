package com.example.cinemate.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class AdminDto {
    private Long id;
    private String username;
    private String email;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}
