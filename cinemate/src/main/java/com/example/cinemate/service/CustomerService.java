package com.example.cinemate.service;

import com.example.cinemate.dto.CustomerRegisterRequestDto;
import com.example.cinemate.dto.CustomerLoginRequestDto;
import com.example.cinemate.entities.Customer;
import com.example.cinemate.repository.CustomerRepository;
import com.example.cinemate.util.PasswordUtil;
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

        // hash password sebelum simpan
        customer.setPassword(PasswordUtil.hashPassword(req.getPassword()));

        return customerRepository.save(customer);
    }

    public Customer login(CustomerLoginRequestDto req) {
        Customer customer = customerRepository.findByEmail(req.getEmail());
        if (customer != null) {
            // hash input password
            String hashedInput = PasswordUtil.hashPassword(req.getPassword());
            if (customer.getPassword().equals(hashedInput)) {
                return customer;
            }
        }
        return null;
    }
}
