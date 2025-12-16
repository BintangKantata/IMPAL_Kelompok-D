package com.example.cinemate.controller;

import com.example.cinemate.dto.ScheduleDto;
import com.example.cinemate.entities.FilmSchedule;
import com.example.cinemate.service.FilmScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*")
public class ScheduleController {

    private final FilmScheduleService scheduleService;

    public ScheduleController(FilmScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{id}")
    public ScheduleDto getSchedule(@PathVariable Long id) {
        FilmSchedule s = scheduleService.findById(id);

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