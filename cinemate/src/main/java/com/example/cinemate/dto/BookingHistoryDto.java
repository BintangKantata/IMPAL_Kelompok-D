package com.example.cinemate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class BookingHistoryDto {
    private Long bookingId;
    private String filmTitle;
    private LocalDate date;
    private LocalTime time;
    private String fnbItems;
    private String locationName;
}