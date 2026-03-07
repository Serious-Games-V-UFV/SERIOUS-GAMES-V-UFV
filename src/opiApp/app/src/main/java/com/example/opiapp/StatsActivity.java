package com.example.opiapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

public class StatsActivity extends BaseActivity { // Cambiado aquí
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        setupBottomNavigation();
    }
}
