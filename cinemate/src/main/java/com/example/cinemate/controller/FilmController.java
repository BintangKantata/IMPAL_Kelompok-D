package com.example.cinemate.controller;

import com.example.cinemate.entities.Film;
import com.example.cinemate.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        return filmRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
