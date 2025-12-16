package com.example.cinemate.repository;

import com.example.cinemate.entities.ScheduleSeat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, Long> {
    List<ScheduleSeat> findBySchedule_IdOrderByCode(Long scheduleId);
    Optional<ScheduleSeat> findBySchedule_IdAndCode(Long scheduleId, String code);
    int countBySchedule_Id(Long id);
    @Modifying
    @Transactional
    void deleteBySchedule_Id(Long scheduleId);
}

