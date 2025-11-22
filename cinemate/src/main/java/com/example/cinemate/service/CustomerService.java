package com.example.cinemate.service;

import com.example.cinemate.dto.CustomerRegisterRequestDto;
import com.example.cinemate.dto.CustomerLoginRequestDto;
import com.example.cinemate.entities.Customer;
import com.example.cinemate.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer register(CustomerRegisterRequestDto req) {
        if (customerRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email sudah terdaftar");
        }
        Customer customer = new Customer();
        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.setEmail(req.getEmail());
        customer.setPassword(req.getPassword());

        System.out.println("Saving customer: " + customer.getEmail());

        // Simpan ke database
        return customerRepository.save(customer); // akan generate id
    }

    public Customer login(CustomerLoginRequestDto req) {
        // Cek user berdasarkan email
        Customer customer = customerRepository.findByEmail(req.getEmail());
        if (customer != null && customer.getPassword().equals(req.getPassword())) {
            return customer;
        }
        return null; // kalau email / password salah
    }
}
