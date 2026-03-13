package com.example.opiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opiapp.network.ApiService;
import com.example.opiapp.network.RetrofitClient;
import com.example.opiapp.network.SessionManager;
import com.example.opiapp.network.model.RegisterRequest;
import com.example.opiapp.network.model.AuthResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etFirstName, etLastName1, etEmail, etPassword, etHeight, etWeight, etDesiredWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = findViewById(R.id.et_first_name);
        etLastName1 = findViewById(R.id.et_last_name1);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etDesiredWater = findViewById(R.id.et_desired_water);

        Button btnRegister = findViewById(R.id.btn_register);
        Button btnBackLogin = findViewById(R.id.btn_back_login);

        btnRegister.setOnClickListener(v -> register());
        btnBackLogin.setOnClickListener(v -> finish());
    }

    private void register() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName1 = etLastName1.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();
        String desiredWaterStr = etDesiredWater.getText().toString().trim();

        if (firstName.isEmpty() || lastName1.isEmpty() || email.isEmpty() ||
                password.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || desiredWaterStr.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest request = new RegisterRequest(
                firstName, lastName1, email, password,
                Integer.parseInt(heightStr),
                Integer.parseInt(weightStr),
                Integer.parseInt(desiredWaterStr)
        );

        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
        api.register(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new SessionManager(RegisterActivity.this).saveToken(response.body().getToken());
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(RegisterActivity.this, "Error " + response.code() + ": " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}