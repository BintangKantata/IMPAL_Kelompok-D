package com.example.cinemate.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequest {
    private Long customerId;
    private List<String> seats;
    private Integer totalPrice;
}