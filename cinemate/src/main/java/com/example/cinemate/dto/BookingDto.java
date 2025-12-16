package com.example.cinemate.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingDto {
    private Long id;
    private Long customerId;
    private Long scheduleId;
    private List<String> seats;
    private Integer totalPrice;
    private String bookedAt;

    private String filmTitle;
    private String filmImage;
}
