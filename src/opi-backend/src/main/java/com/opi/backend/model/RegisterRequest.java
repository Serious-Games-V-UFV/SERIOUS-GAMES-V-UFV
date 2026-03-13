package com.opi.backend.model;

public class RegisterRequest {
    private String firstName;
    private String lastName1;
    private String email;
    private String password;
    private int height;
    private int weight;
    private int desiredWater;

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName1() { return lastName1; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getHeight() { return height; }
    public int getWeight() { return weight; }
    public int getDesiredWater() { return desiredWater; }
}