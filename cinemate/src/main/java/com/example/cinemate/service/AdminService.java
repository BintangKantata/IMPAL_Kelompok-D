package com.example.cinemate.service;

import com.example.cinemate.dto.AdminLoginRequestDto;
import com.example.cinemate.entities.Admin;
import com.example.cinemate.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    public Admin login(AdminLoginRequestDto req) {
        Admin admin = adminRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Email tidak ditemukan"));

        if (!admin.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Password salah");
        }

        return admin;
    }

    public Admin getAdminById(Long id) {
        return adminRepo.findById(id).orElse(null);
    }
}
