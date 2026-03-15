package com.example.opiapp;

import android.os.Bundle;


public class SocialActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);


        setupBottomNavigation();
    }
}
