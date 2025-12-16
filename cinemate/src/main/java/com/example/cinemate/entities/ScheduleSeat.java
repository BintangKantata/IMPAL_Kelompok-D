package com.example.cinemate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schedule_seats", uniqueConstraints = @UniqueConstraint(columnNames = {"schedule_id","code"}))
public class ScheduleSeat {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private FilmSchedule schedule;

    // contoh: "A1", "B5"
    @Getter
    @Setter
    @Column(nullable = false)
    private String code;

    // apakah sudah dibooking
    @Getter
    @Setter
    private boolean booked = false;

    // optional: siapa yg booking (nullable)
    @Getter
    @Setter
    private Long bookedByCustomerId;
}

