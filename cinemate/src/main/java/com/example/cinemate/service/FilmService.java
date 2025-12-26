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

    public Film findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));
    }

    public Film createFilm(Film film) {
        film.setTitle(film.getTitle());
        film.setDescription(film.getDescription());
        film.setImage(film.getImage());
        film.setDuration(film.getDuration());
        film.setGenres(film.getGenres());
        film.setRu(film.getRu());

        return repo.save(film);
    }

    public Film updateFilm(Long id, Film updated) {
        Film film = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        film.setTitle(updated.getTitle());
        film.setGenres(updated.getGenres());
        film.setDuration(updated.getDuration());
        film.setImage(updated.getImage());
        film.setDescription(updated.getDescription());
        film.setRu(updated.getRu());

        return repo.save(film);
    }

    public void deleteFilm(Long id) {
        repo.deleteById(id);
    }
}
