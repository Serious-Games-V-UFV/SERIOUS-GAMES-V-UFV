package com.example.opiapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;

import com.example.opiapp.network.SessionManager;

public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupBottomNavigation();

        AppCompatButton btnLogout = findViewById(R.id.btn_logout_dark);
        btnLogout.setOnClickListener(v -> {
            new SessionManager(this).clear();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
