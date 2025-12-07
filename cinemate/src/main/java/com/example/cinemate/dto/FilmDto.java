package com.example.cinemate.dto;

import com.example.cinemate.entities.Genre;
import com.example.cinemate.entities.RatingUsia;
import lombok.Data;

import java.util.Set;

@Data
public class FilmDto {
    private Long id;
    private String title;
    private Set<Genre> genres;
    private RatingUsia ru;
    private int duration;
    private String image;
    private String description;
}
