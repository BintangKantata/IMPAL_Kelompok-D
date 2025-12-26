package com.example.cinemate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fnb_items")
public class FNBItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 500)
    private String description;

    private Long price; // rupiah

    @Enumerated(EnumType.STRING)
    private FnbType type;

    private Integer quantity;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public FNBItem() {}
}

