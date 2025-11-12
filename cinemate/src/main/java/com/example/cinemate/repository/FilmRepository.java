package com.example.cinemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cinemate.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}