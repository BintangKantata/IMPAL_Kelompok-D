package com.example.cinemate.repository;

import com.example.cinemate.entities.FNBItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FNBItemRepository extends JpaRepository<FNBItem, Long> {
    List<FNBItem> findByLocationId(Long locationId);
}

