package com.example.opiapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

        TextView name = findViewById(R.id.label_name_profile);
        TextView email = findViewById(R.id.label_email_profile);

        executor.execute(() -> {
            if (db == null) {
                db = new Database();
            }
            String nameVal = db.getDatum("account", "CONCAT(first_name,' ',last_name1)", currentUser);
            String emailVal = db.getDatum("account", "email", currentUser);

            runOnUiThread(() -> {
                if (name != null) name.setText(nameVal);
                if (email != null) email.setText(emailVal);
            });
        });
    }
}
