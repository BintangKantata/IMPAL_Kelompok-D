package com.example.cinemate.controller;

import com.example.cinemate.dto.AdminDto;
import com.example.cinemate.dto.AdminLoginRequestDto;
import com.example.cinemate.entities.Admin;
import com.example.cinemate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequestDto req) {
        try {
            Admin admin = adminService.login(req);

            AdminDto dto = new AdminDto();
            dto.setId(admin.getId());
            dto.setUsername(admin.getUsername());
            dto.setEmail(admin.getEmail());

            return ResponseEntity.ok(dto);

        } catch (RuntimeException e) {
            Map<String, String> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }

    // PROFILE ADMIN (setelah login)
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if (admin == null) {
            return ResponseEntity.status(404).body("Admin not found");
        }

        AdminDto dto = new AdminDto();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setEmail(admin.getEmail());

        return ResponseEntity.ok(dto);
    }
}
