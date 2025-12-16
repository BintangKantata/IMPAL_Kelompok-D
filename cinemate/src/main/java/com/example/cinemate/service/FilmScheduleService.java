package com.example.cinemate.service;

import com.example.cinemate.entities.Auditorium;
import com.example.cinemate.entities.Film;
import com.example.cinemate.entities.FilmSchedule;
import com.example.cinemate.entities.Location;
import com.example.cinemate.repository.AuditoriumRepository;
import com.example.cinemate.repository.FilmRepository;
import com.example.cinemate.repository.FilmScheduleRepository;
import com.example.cinemate.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FilmScheduleService {
    private final FilmScheduleRepository repo;
    private final FilmRepository filmRepo;
    private final LocationRepository locRepo;
    private final AuditoriumRepository audRepo;
    private final ScheduleSeatService seatService;

    public FilmScheduleService(FilmScheduleRepository repo, FilmRepository filmRepo,
                               LocationRepository locRepo, AuditoriumRepository audRepo, ScheduleSeatService seatService) {
        this.repo = repo; this.filmRepo = filmRepo; this.locRepo = locRepo; this.audRepo = audRepo;
        this.seatService = seatService;
    }

    public FilmSchedule createSchedule(
            Long filmId,
            Long locationId,
            Long auditoriumId,
            LocalDate date,
            LocalTime time
    ) {
        Film film = filmRepo.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));
        Location loc = locRepo.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Auditorium aud = audRepo.findById(auditoriumId)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        LocalTime endTime = time.plusMinutes(film.getDuration());

        List<FilmSchedule> overlaps =
                repo.findOverlapping(locationId, auditoriumId, date, time, endTime);

        if (!overlaps.isEmpty()) {
            throw new RuntimeException("Time slot already taken!");
        }

        FilmSchedule fs = new FilmSchedule();
        fs.setFilm(film);
        fs.setLocation(loc);
        fs.setAuditorium(aud);
        fs.setDate(date);
        fs.setTime(time);
        fs.setEndTime(endTime);

        FilmSchedule saved = repo.save(fs);

        seatService.generateSeats(saved);

        return saved;
    }

    public List<FilmSchedule> findByFilm(Long filmId) {
        return repo.findByFilmId(filmId);
    }

    public FilmSchedule update(Long id, LocalDate date, LocalTime time,
                               Long locationId, Long auditoriumId) {

        FilmSchedule fs = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Film film = fs.getFilm();
        LocalTime newEnd = time.plusMinutes(film.getDuration());

        List<FilmSchedule> conflicts =
                repo.findOverlapping(locationId, auditoriumId, date, time, newEnd)
                        .stream()
                        .filter(s -> !s.getId().equals(id))
                        .toList();

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Time slot already taken!");
        }

        fs.setDate(date);
        fs.setTime(time);
        fs.setEndTime(newEnd);
        fs.setLocation(locRepo.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found")));
        fs.setAuditorium(audRepo.findById(auditoriumId)
                .orElseThrow(() -> new RuntimeException("Auditorium not found")));

        FilmSchedule saved = repo.save(fs);

        // 🟩 Tambahkan ini
        seatService.deleteSeatsBySchedule(id);   // hapus seat lama
        seatService.generateSeats(saved);        // generate seat baru

        return saved;
    }


    @Transactional
    public void delete(Long id) {
        seatService.deleteSeatsBySchedule(id);
        repo.deleteById(id);
    }

    public FilmSchedule findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
    }

}