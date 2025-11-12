package com.example.cinemate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String judul;
    private String genre;
    private int tahun;

    // Getter dan Setter
}