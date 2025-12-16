package com.example.cinemate.service;

import com.example.cinemate.entities.Booking;
import com.example.cinemate.entities.BookingRequest;
import com.example.cinemate.entities.FilmSchedule;
import com.example.cinemate.entities.ScheduleSeat;
import com.example.cinemate.repository.BookingRepository;
import com.example.cinemate.repository.FilmScheduleRepository;
import com.example.cinemate.repository.ScheduleSeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScheduleSeatService {

    private final ScheduleSeatRepository seatRepo;
    private final FilmScheduleRepository scheduleRepo;
    private final BookingRepository bookingRepo;

    @Transactional
    public void deleteSeatsBySchedule(Long scheduleId) {
        seatRepo.deleteBySchedule_Id(scheduleId);
    }

    public void generateSeats(FilmSchedule schedule) {
        // kalau seats sudah ada, jangan generate lagi
        if (seatRepo.countBySchedule_Id(schedule.getId()) > 0) return;

        List<ScheduleSeat> seats = new ArrayList<>();

        for (char row = 'A'; row <= 'J'; row++) {
            for (int i = 1; i <= 10; i++) {
                ScheduleSeat s = new ScheduleSeat();
                s.setSchedule(schedule);
                s.setCode(row + String.valueOf(i));
                seats.add(s);
            }
        }

        seatRepo.saveAll(seats);
    }

    public List<ScheduleSeat> getSeats(Long scheduleId) {
        return seatRepo.findBySchedule_IdOrderByCode(scheduleId);
    }

    public Map<String, Object> bookSeats(Long scheduleId, BookingRequest req) {

        FilmSchedule schedule = scheduleRepo.findById(scheduleId).orElse(null);
        if (schedule == null) {
            throw new RuntimeException("Schedule not found");
        }

        // cek semua kursi valid & belum dibooking
        for (String code : req.getSeats()) {
            ScheduleSeat seat = seatRepo.findBySchedule_IdAndCode(scheduleId, code)
                    .orElseThrow(() -> new RuntimeException("Seat " + code + " not found"));

            if (seat.isBooked()) {
                throw new RuntimeException("Seat " + code + " already booked");
            }
        }

        // tandai kursi sebagai booked
        for (String code : req.getSeats()) {
            ScheduleSeat seat = seatRepo.findBySchedule_IdAndCode(scheduleId, code).get();
            seat.setBooked(true);
            seat.setBookedByCustomerId(req.getCustomerId());
            seatRepo.save(seat);
        }

        // simpan Booking
        Booking booking = new Booking();
        booking.setCustomerId(req.getCustomerId());
        booking.setSchedule(schedule);
        booking.setSeats(String.join(",", req.getSeats()));
        booking.setTotalPrice(req.getTotalPrice());
        bookingRepo.save(booking);

        // response sederhana
        Map<String, Object> result = new HashMap<>();
        result.put("scheduleId", scheduleId);
        result.put("bookingId", booking.getId());
        result.put("status", "success");
        result.put("seats", req.getSeats());

        return result;
    }
}