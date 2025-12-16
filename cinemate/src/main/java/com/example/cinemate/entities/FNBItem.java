package com.example.cinemate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "fnb_items")
public class FNBItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public FNBItem() {}

    public FNBItem(String name, Integer quantity, Location location) {
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }
}

