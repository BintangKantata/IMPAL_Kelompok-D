package com.example.cinemate.service;

import com.example.cinemate.entities.Auditorium;
import com.example.cinemate.entities.Location;
import com.example.cinemate.repository.AuditoriumRepository;
import com.example.cinemate.repository.LocationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepo;
    private final AuditoriumRepository auditoriumRepo;

    @PostConstruct
    public void testDb() {
        System.out.println("LOCATIONS IN DB: " + locationRepo.findAll());
    }

    public List<Auditorium> getAuditoriums(Long locId) {
        System.out.println("Fetching auditoriums for location id = " + locId);
        List<Auditorium> list = auditoriumRepo.findByLocationId(locId);
        System.out.println("Result = " + list);
        return list;
    }


    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    public Location create(String name) {
        Location l = new Location();
        l.setName(name);
        return locationRepo.save(l);
    }

}

