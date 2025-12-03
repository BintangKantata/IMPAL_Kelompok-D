package com.example.cinemate.service;

import com.example.cinemate.dto.AdminLoginRequestDto;
import com.example.cinemate.entities.Admin;
import com.example.cinemate.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin login(AdminLoginRequestDto req) {
        Admin admin = adminRepository.findByEmail(req.getEmail());

        if (admin == null || !admin.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Invalid admin email or password");
        }
        return admin;
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }
}

