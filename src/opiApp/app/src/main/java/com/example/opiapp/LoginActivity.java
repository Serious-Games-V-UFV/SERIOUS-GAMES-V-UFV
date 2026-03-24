package com.example.opiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (currentUser != -1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        EditText emailTF = findViewById(R.id.et_email);
        EditText passwordTF = findViewById(R.id.et_password);
        Button loginBtn = findViewById(R.id.btn_login);
        Button registerBtn = findViewById(R.id.btn_register);
        loginBtn.setOnClickListener(v -> {
            String email = emailTF.getText().toString().trim();
            String password = passwordTF.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            executor.execute(() -> {
                if (db == null) {
                    db = new Database();
                }

                int id = db.getuserID(email);
                if (id != -1) {
                    if (db.validateUser(email, password)) {
                        runOnUiThread(() -> {
                            saveUserSession(id);
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show());
                }
            });
        });
        registerBtn.setOnClickListener(v ->{
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
