package com.example.cinemate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "film_genres", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "genre")
    private Set<Genre> genres;

    @Enumerated(EnumType.STRING)
    private RatingUsia ru;

    private int duration;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description; // synopsis
}
