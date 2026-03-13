package com.example.opiapp.network;

import com.example.opiapp.network.model.AuthRequest;
import com.example.opiapp.network.model.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/auth/login")
    Call<AuthResponse> login(@Body AuthRequest request);

    @POST("api/auth/register")
    Call<AuthResponse> register(@Body AuthRequest request);
}