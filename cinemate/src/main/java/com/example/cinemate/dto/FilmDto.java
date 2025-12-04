package com.example.cinemate.dto;

import com.example.cinemate.entities.Genre;
import lombok.Data;

import java.util.Set;

@Data
public class FilmDto {
    private Long id;
    private String title;
    private Set<Genre> genres;
    private int duration;
    private int tahun;
    private String description;
}
