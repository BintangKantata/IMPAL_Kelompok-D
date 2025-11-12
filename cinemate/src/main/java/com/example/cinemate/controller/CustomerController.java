package com.example.cinemate.controller;

import com.example.cinemate.dto.CustomerDto;
import com.example.cinemate.dto.CustomerRegisterRequestDto;
import com.example.cinemate.dto.CustomerLoginRequestDto;
import com.example.cinemate.entities.Customer;
import com.example.cinemate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"}) // agar dapat diakses dari frontend lokal
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public CustomerDto register(@RequestBody CustomerRegisterRequestDto req) {
        Customer savedCustomer = customerService.register(req);

        // Konversi ke DTO sebelum return
        CustomerDto dto = new CustomerDto();
        dto.setId(savedCustomer.getId());
        dto.setFirstName(savedCustomer.getFirstName());
        dto.setLastName(savedCustomer.getLastName());
        dto.setEmail(savedCustomer.getEmail());
        dto.setPassword(savedCustomer.getPassword());

        return dto;
    }

    @PostMapping("/login")
    public CustomerDto login(@RequestBody CustomerLoginRequestDto req) {
        Customer customer = customerService.login(req);

        if (customer == null) {
            throw new RuntimeException("Invalid email or password");
        }

        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());

        return dto;
    }
}
