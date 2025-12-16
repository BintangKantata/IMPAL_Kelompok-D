package com.example.cinemate.controller;

import com.example.cinemate.entities.Booking;
import com.example.cinemate.entities.BookingRequest;
import com.example.cinemate.entities.FilmSchedule;
import com.example.cinemate.entities.ScheduleSeat;
import com.example.cinemate.repository.BookingRepository;
import com.example.cinemate.repository.FilmScheduleRepository;
import com.example.cinemate.repository.ScheduleSeatRepository;
import com.example.cinemate.service.FilmScheduleService;
import com.example.cinemate.service.ScheduleSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ScheduleSeatController {

    private final ScheduleSeatService seatService;
    private final FilmScheduleRepository scheduleRepo;
    private final BookingRepository bookingRepo;

    @GetMapping("/{scheduleId}/seats")
    public ResponseEntity<?> getSeats(@PathVariable Long scheduleId) {
        if (!scheduleRepo.existsById(scheduleId)) {
            return ResponseEntity.status(404).body("Schedule not found");
        }

        return ResponseEntity.ok(seatService.getSeats(scheduleId));
    }

    @PostMapping("/{scheduleId}/book")
    public ResponseEntity<?> bookSeats(
            @PathVariable Long scheduleId,
            @RequestBody BookingRequest req
    ) {
        try {
            Map<String, Object> result = seatService.bookSeats(scheduleId, req);
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}