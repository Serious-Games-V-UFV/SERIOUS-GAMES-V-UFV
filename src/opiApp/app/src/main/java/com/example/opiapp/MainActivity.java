package com.example.opiapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends BaseActivity {

    private TextView tvProgressValue;
    private Button btnAdd;
    private double currentHydration = 0.0;
    private final double targetHydration = 5;   // cambiarlo a que sea el valor de la variable de la base de datos aguaDeseada
    private boolean bottlePlaced = false;
    private int hoursSinceDrink = 0;
    private double capacity = 750;
    private String[] activity = new String[4];
    Bluetooth btcon = new Bluetooth();



    private static final String CHANNEL_ID = "hydration_notifications";
    private static final int NOTIFICATION_ID = 1;

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();
        // TODO: Integrate it with ScheduledExecutorService (https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html)
            WaterData receivedData = btcon.readData(true);
            processWaterData(receivedData);

        createNotificationChannel();
        requestNotificationPermission();

        // Handle window insets for Edge-to-Edge
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Initialize UI elements
        tvProgressValue = findViewById(R.id.tv_progress_value);
        btnAdd = findViewById(R.id.btn_add);

        // Example interaction in Java
        if (btnAdd != null) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addWater(0.25);
                }
            });
        }
    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Hydration Goal";
            String description = "Notifications for reaching hydration goal";
            // IMPORTANCE_HIGH permite que la notificación aparezca como un banner (pop-up)
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Meta conseguida")
                .setContentText("¡Felicidades! Has llegado a tu meta de hidratación.")
                // PRIORITY_HIGH es necesario para dispositivos anteriores a Android 8.0
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Esto hará que vibre y suene si el dispositivo lo permite
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private int addWater(double amount) {
        try{
            currentHydration += amount;
            if (currentHydration >= targetHydration) {
                currentHydration = targetHydration;
                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
                sendNotification();
            }

            // Update the UI
            String progressText = String.format("%.2fL / %.1fL", currentHydration, targetHydration);
            tvProgressValue.setText(progressText);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * Process the data received by the bluetooth connection
     * @param dataStream data received
     */
    private void processWaterData(WaterData dataStream){
        capacity = dataStream.capacity;
        currentHydration += dataStream.totalDrunk;
        bottlePlaced = dataStream.bottlePlaced;
        hoursSinceDrink = dataStream.hoursSinceDrink;
    }

}
