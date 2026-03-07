package com.example.opiapp;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Base class for activities that share common functionality like the bottom navigation bar.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Initializes and configures the click listeners for the bottom navigation bar.
     * It maps each TextView to its corresponding Activity, handling transitions
     * through Intents while preventing the current Activity from restarting
     * if the user is already on the selected screen.
     */
    protected void setupBottomNavigation() {
        TextView navHome = findViewById(R.id.navHome);
        TextView navStats = findViewById(R.id.navStats);
        TextView navSocial = findViewById(R.id.navSocial);
        TextView navTools = findViewById(R.id.navTools);

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                if (!(this instanceof MainActivity)) {
                    startActivity(new Intent(this, MainActivity.class));
                }
            });
        }

        if (navStats != null) {
            navStats.setOnClickListener(v -> {
                if (!(this instanceof StatsActivity)) {
                    startActivity(new Intent(this, StatsActivity.class));
                }
            });
        }


        if (navSocial != null) {
            navSocial.setOnClickListener(v -> {
                startActivity(new Intent(this, SocialActivity.class));
            });
        }

        if (navTools != null) {
            navTools.setOnClickListener(v -> {
                startActivity(new Intent(this, ProfileActivity.class));
            });
        }
    }
}