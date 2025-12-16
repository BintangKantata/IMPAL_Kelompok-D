package com.example.cinemate.controller;

import com.example.cinemate.dto.ScheduleDto;
import com.example.cinemate.dto.ScheduleRequestDto;
import com.example.cinemate.entities.Auditorium;
import com.example.cinemate.entities.FilmSchedule;
import com.example.cinemate.entities.Location;
import com.example.cinemate.service.FilmScheduleService;
import com.example.cinemate.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminScheduleController {

    private final LocationService locationService;
    private final FilmScheduleService scheduleService;

    public AdminScheduleController(LocationService locationService, FilmScheduleService scheduleService) {
        this.locationService = locationService;
        this.scheduleService = scheduleService;
    }

    // Locations
    @GetMapping("/locations")
    public List<Location> getLocations() { return locationService.findAll(); }

    @PostMapping("/locations")
    public ResponseEntity<?> createLocation(@RequestBody Map<String,String> body) {
        String name = body.get("name");
        Location loc = locationService.create(name);
        return ResponseEntity.ok(loc);
    }

    @GetMapping("/locations/{id}/auditoriums")
    public List<Auditorium> getAuditoriums(@PathVariable Long id) {
        return locationService.getAuditoriums(id);
    }

    // Schedules
    @PostMapping("/schedules")
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDto req) {
        try {
            FilmSchedule s = scheduleService.createSchedule(
                    req.getFilmId(),
                    req.getLocationId(),
                    req.getAuditoriumId(),
                    LocalDate.parse(req.getDate()),
                    LocalTime.parse(req.getTime())
            );
            return ResponseEntity.ok(toDto(s));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/films/{filmId}/schedules")
    public List<ScheduleDto> getSchedulesForFilm(@PathVariable Long filmId) {
        return scheduleService.findByFilm(filmId).stream().map(this::toDto).toList();
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto req) {
        try {
            FilmSchedule s = scheduleService.update(id, LocalDate.parse(req.getDate()), LocalTime.parse(req.getTime()), req.getLocationId(), req.getAuditoriumId());
            return ResponseEntity.ok(toDto(s));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok(Map.of("status","deleted"));
    }

    private ScheduleDto toDto(FilmSchedule s) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(s.getId());
        dto.setFilmId(s.getFilm().getId());
        dto.setFilmTitle(s.getFilm().getTitle());
        dto.setLocationId(s.getLocation().getId());
        dto.setLocationName(s.getLocation().getName());
        dto.setAuditoriumId(s.getAuditorium().getId());
        dto.setAuditoriumName(s.getAuditorium().getName());
        dto.setDate(s.getDate().toString());
        dto.setTime(s.getTime().toString());
        return dto;
    }
}

