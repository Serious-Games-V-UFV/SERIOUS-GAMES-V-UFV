package com.example.opiapp;

public class WaterData extends BaseActivity {

    public WaterData(double capacity, int hoursSinceDrink, double totalDrunk, boolean bottlePlaced){
        this.capacity = capacity;
        this.hoursSinceDrink = hoursSinceDrink;
        this.totalDrank = totalDrunk;
        this.isBottlePlaced = bottlePlaced;
    }
}