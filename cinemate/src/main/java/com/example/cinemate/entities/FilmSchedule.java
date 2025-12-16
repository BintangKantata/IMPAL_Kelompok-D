package com.example.cinemate.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "film_schedules",
        uniqueConstraints = @UniqueConstraint(columnNames = {"film_id","location_id","auditorium_id","date","time"}))
public class FilmSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    private LocalDate date;
    private LocalTime time;
    private LocalTime endTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public Auditorium getAuditorium() { return auditorium; }
    public void setAuditorium(Auditorium auditorium) { this.auditorium = auditorium; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
