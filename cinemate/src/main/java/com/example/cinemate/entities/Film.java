package com.example.cinemate.entities;

import jakarta.persistence.*;

import java.util.Set;

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

    private int duration;

    private int tahun;

    @Column(columnDefinition = "TEXT")
    private String description; // deskripsi film

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) { this.genres = genres; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
