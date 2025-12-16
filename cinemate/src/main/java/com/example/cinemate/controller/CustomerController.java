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
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginRequestDto req) {
        Customer customer = customerService.login(req);

        if (customer == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Email atau password tidak valid");
            return ResponseEntity.status(400).body(err);
        }

        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        return ResponseEntity.ok(dto);
    }
}
