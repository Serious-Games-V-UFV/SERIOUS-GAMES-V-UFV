package com.example.opiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Objects;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailTF = findViewById(R.id.et_email);
        EditText passwordTF = findViewById(R.id.et_password);
        Button loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(v -> {
            String email = emailTF.getText().toString().trim();
            String password = passwordTF.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Realizamos la consulta en el hilo secundario que ya tenemos en BaseActivity
            executor.execute(() -> {
                if (db == null) {
                    runOnUiThread(() -> Toast.makeText(this, "Conectando con el servidor, reintente en un momento", Toast.LENGTH_SHORT).show());
                    return;
                }

                int id = db.getuserID(email);
                if (id != -1) {
                    String passwordDB = db.getDatum("account", "password", id);
                    
                    runOnUiThread(() -> {
                        if (Objects.equals(passwordDB, password)) {
                            currentUser = id;
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            finish(); // Cerramos el login para que no se pueda volver atrás
                        } else {
                            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }
}
