package com.example.opiapp;

public class WaterData {

    public double capacity;
    public int hoursSinceDrink;
    public double totalDrunk;
    public boolean bottlePlaced;

    public WaterData(double capacity, int hoursSinceDrink, double totalDrunk, boolean bottlePlaced) {
        this.capacity = capacity;
        this.hoursSinceDrink = hoursSinceDrink;
        this.totalDrunk = totalDrunk;
        this.bottlePlaced = bottlePlaced;
    }
}