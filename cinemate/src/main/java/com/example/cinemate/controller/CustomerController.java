package com.example.cinemate.controller;

import com.example.cinemate.dto.CustomerDto;
import com.example.cinemate.dto.CustomerRegisterRequestDto;
import com.example.cinemate.dto.CustomerLoginRequestDto;
import com.example.cinemate.entities.Customer;
import com.example.cinemate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRegisterRequestDto req) {
        try {
            Customer savedCustomer = customerService.register(req);

            CustomerDto dto = new CustomerDto();
            dto.setId(savedCustomer.getId());
            dto.setFirstName(savedCustomer.getFirstName());
            dto.setLastName(savedCustomer.getLastName());
            dto.setEmail(savedCustomer.getEmail());

            return ResponseEntity.ok(dto);

        } catch (RuntimeException e) {
            // Kirim error JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/login")
    public CustomerDto login(@RequestBody CustomerLoginRequestDto req) {
        Customer customer = customerService.login(req);

        if (customer == null) {
            throw new RuntimeException("Email atau password tidak valid");
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
