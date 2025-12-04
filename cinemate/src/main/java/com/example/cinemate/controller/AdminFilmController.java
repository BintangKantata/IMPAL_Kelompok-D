package com.example.cinemate.controller;

import com.example.cinemate.entities.Film;
import com.example.cinemate.service.FilmService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/films")
@CrossOrigin(origins = "*")
public class AdminFilmController {

    private final FilmService service;

    public AdminFilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping
    public List<Film> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        return service.createFilm(film);
    }

    @PutMapping("/{id}")
    public Film update(@PathVariable Long id, @RequestBody Film film) {
        return service.updateFilm(id, film);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteFilm(id);
    }
}
