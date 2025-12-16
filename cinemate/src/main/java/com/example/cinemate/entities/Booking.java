package com.example.cinemate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private FilmSchedule schedule;

    private String seats;

    private Integer totalPrice;

    private LocalDateTime bookedAt = LocalDateTime.now();
}