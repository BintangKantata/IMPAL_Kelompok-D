package com.example.cinemate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class BookingDetailDto {
    private Long bookingId;
    private String filmTitle;
    private String filmImage;
    private String locationName;
    private LocalDate date;
    private LocalTime time;
    private String seats;
    private Integer totalPrice;
}
