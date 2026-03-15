package com.example.opiapp;

public class WaterData  {
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getHoursSinceDrink() {
        return hoursSinceDrink;
    }

    public void setHoursSinceDrink(int hoursSinceDrink) {
        this.hoursSinceDrink = hoursSinceDrink;
    }

    public double getTotalDrunk() {
        return totalDrunk;
    }

    public void setTotalDrunk(double totalDrunk) {
        this.totalDrunk = totalDrunk;
    }

    public boolean isBottlePlaced() {
        return isBottlePlaced;
    }

    public void setBottlePlaced(boolean bottlePlaced) {
        isBottlePlaced = bottlePlaced;
    }

    private double capacity;
private int hoursSinceDrink;
private double totalDrunk;
private boolean isBottlePlaced;
    public WaterData(double capacity, int hoursSinceDrink, double totalDrunk, boolean isBottlePlaced){
        this.capacity = capacity;
        this.hoursSinceDrink = hoursSinceDrink;
        this.totalDrunk = totalDrunk;
        this.isBottlePlaced = isBottlePlaced;
    }


}