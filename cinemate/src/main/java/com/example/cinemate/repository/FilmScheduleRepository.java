package com.example.cinemate.repository;

import com.example.cinemate.entities.FilmSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FilmScheduleRepository extends JpaRepository<FilmSchedule, Long> {
    List<FilmSchedule> findByFilmId(Long filmId);
    List<FilmSchedule> findByLocationIdAndAuditoriumIdAndDate(Long locationId, Long auditoriumId, LocalDate date);

    @Query("SELECT s FROM FilmSchedule s " +
            "WHERE s.location.id = :locationId " +
            "AND s.auditorium.id = :auditoriumId " +
            "AND s.date = :date " +
            "AND (:startTime < s.endTime AND :endTime > s.time)")

    List<FilmSchedule> findOverlapping(
            Long locationId,
            Long auditoriumId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );

}

