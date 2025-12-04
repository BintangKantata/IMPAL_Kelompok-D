package com.example.cinemate.service;

import com.example.cinemate.entities.Film;
import com.example.cinemate.repository.FilmRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilmService {

    private final FilmRepository repo;

    public FilmService(FilmRepository repo) {
        this.repo = repo;
    }

    public List<Film> findAll() {
        return repo.findAll();
    }

    public Film createFilm(Film film) {
        film.setTitle(film.getTitle());
        film.setDescription(film.getDescription());
        film.setTahun(film.getTahun());
        film.setDuration(film.getDuration());
        film.setGenres(film.getGenres());

        return repo.save(film);
    }

    public Film updateFilm(Long id, Film updated) {
        Film film = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        film.setTitle(updated.getTitle());
        film.setGenres(updated.getGenres());
        film.setDuration(updated.getDuration());
        updated.setTahun(film.getTahun());
        film.setDescription(updated.getDescription());

        return repo.save(film);
    }

    public void deleteFilm(Long id) {
        repo.deleteById(id);
    }
}

