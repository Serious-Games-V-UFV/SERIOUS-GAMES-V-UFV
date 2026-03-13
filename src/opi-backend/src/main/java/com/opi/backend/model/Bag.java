package com.opi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bag")
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String type;

    @Column(nullable = false, length = 45)
    private String color;

    private LocalDate firstConnection;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getFirstConnection() {
        return firstConnection;
    }

    public void setFirstConnection(LocalDate firstConnection) {
        this.firstConnection = firstConnection;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}