package com.example.cinemate.service;

import com.example.cinemate.entities.Admin;
import com.example.cinemate.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInitializer {

    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    public void initAdmin() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setEmail("admin@cinemate.com");
            admin.setPassword("admin123"); // bisa diganti hash

            adminRepository.save(admin);
        }
    }
}

