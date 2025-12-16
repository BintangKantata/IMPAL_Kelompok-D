package com.example.cinemate.controller;

import com.example.cinemate.dto.BookingDto;
import com.example.cinemate.entities.Booking;
import com.example.cinemate.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepo;

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable Long id) {

        Booking b = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        BookingDto dto = new BookingDto();
        dto.setId(b.getId());
        dto.setCustomerId(b.getCustomerId());
        dto.setScheduleId(b.getSchedule().getId());
        dto.setSeats(Arrays.asList(b.getSeats().split(",")));
        dto.setTotalPrice(b.getTotalPrice());
        dto.setBookedAt(b.getBookedAt().toString());

        // tambahan paling penting
        dto.setFilmTitle(b.getSchedule().getFilm().getTitle());
        dto.setFilmImage(b.getSchedule().getFilm().getImage());

        return dto;
    }
}

