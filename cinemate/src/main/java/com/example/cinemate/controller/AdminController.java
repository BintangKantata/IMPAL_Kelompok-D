package com.example.cinemate.controller;

import com.example.cinemate.dto.AdminDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    // Contoh endpoint sederhana (kamu bisa kembangkan sesuai kebutuhan)
    @GetMapping("/profile")
    public AdminDto getProfile() {
        AdminDto dto = new AdminDto();
        dto.setId(1L);
        dto.setUsername("admin");
        dto.setEmail("admin@cinemate.com");
        return dto;
    }
}