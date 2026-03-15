package com.example.opiapp;

import android.os.Bundle;
import android.widget.Button;

public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupBottomNavigation();

        Button logoutBtn = findViewById(R.id.btn_logout_dark);
        if (logoutBtn == null) {
            logoutBtn = findViewById(R.id.btn_logout_light);
        }

        if (logoutBtn != null) {
            logoutBtn.setOnClickListener(v -> logout());
        }
    }
}
