package com.opi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_reminder")
public class DailyReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @Column(nullable = false)
    private Integer amountDrunk;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAmountDrunk() {
        return amountDrunk;
    }

    public void setAmountDrunk(Integer amountDrunk) {
        this.amountDrunk = amountDrunk;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}