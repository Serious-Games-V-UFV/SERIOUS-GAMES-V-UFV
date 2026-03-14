package com.example.opiapp;

public class WaterData  {
double capacity;
int hoursSinceDrink;
double totalDrank;
boolean isBottlePlaced;
    public WaterData(double capacity, int hoursSinceDrink, double totalDrunk, boolean isBottlePlaced){
        this.capacity = capacity;
        this.hoursSinceDrink = hoursSinceDrink;
        this.totalDrank = totalDrunk;
        this.isBottlePlaced = isBottlePlaced;
    }


}