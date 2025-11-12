package com.example.cinemate.repository;

import com.example.cinemate.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Kamu bisa tambahkan query custom nanti di sini, misalnya:
    Customer findByEmail(String email);
}
