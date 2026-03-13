package com.example.opiapp.network.model;

public class RegisterRequest {
    private String firstName;
    private String lastName1;
    private String email;
    private String password;
    private int height;
    private int weight;
    private int desiredWater;

    public RegisterRequest(String firstName, String lastName1, String email,
                           String password, int height, int weight, int desiredWater) {
        this.firstName = firstName;
        this.lastName1 = lastName1;
        this.email = email;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.desiredWater = desiredWater;
    }
}