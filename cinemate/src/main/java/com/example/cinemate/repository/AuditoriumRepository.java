package com.example.cinemate.repository;

import com.example.cinemate.entities.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    List<Auditorium> findByLocationId(Long locationId);
}

