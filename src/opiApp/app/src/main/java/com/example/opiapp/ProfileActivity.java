package com.example.opiapp;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;



public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupBottomNavigation();
    }
}