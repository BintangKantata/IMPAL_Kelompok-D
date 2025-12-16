package com.example.cinemate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // optional bidirectional
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Auditorium> auditoriums;


    @Override
    public String toString() {
        return "Location{id=" + id + ", name='" + name + "'}";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Auditorium> getAuditoriums() { return auditoriums; }
    public void setAuditoriums(List<Auditorium> auditoriums) { this.auditoriums = auditoriums; }
}

