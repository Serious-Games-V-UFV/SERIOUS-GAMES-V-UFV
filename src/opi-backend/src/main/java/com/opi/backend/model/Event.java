package com.opi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String type;

    @Column(nullable = false, length = 45)
    private String data;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "bag_id", nullable = false)
    private Bag bag;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Bag getBottle() {
        return bag;
    }

    public void setBottle(Bag bag) {
        this.bag = bag;
    }
}