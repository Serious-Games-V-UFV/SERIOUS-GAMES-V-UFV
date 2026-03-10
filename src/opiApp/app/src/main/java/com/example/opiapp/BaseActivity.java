package com.example.opiapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

/**
 * Base class for activities that share common functionality like the bottom navigation bar.
 * the notification send
 */
public class BaseActivity extends AppCompatActivity {
    
    /**
     * Allows the notification to be on top of the screen
     */
    final static int NOTIFICATION_PRIORITY = NotificationManager.IMPORTANCE_HIGH;
    protected static final String CHANNEL_ID = "hydration_notifications";
    protected static final int NOTIFICATION_ID = 1;
    protected double totalDrank = 0.0;
    protected final double targetHydration = 5;
    protected boolean isBottlePlaced = false;
    protected int hoursSinceDrink = 0;
    protected double capacity = 750;
    protected boolean reached = false;
    Bluetooth btcon = new Bluetooth();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestNotificationPermission();
        createNotificationChannel("Hydration Goal","Notifications for reaching hydration goal");

        // FIXME FOLLOWING METHODS CANT BE CALLED AT THE SAME EXEC (ONE IS FAKING WEIGHT REDUCTION)
        // processWaterData(btcon.readData(true));
        processWaterData(btcon.readData(true),true);
    }

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
    /**
     * Launcher for requesting runtime permissions from the user.
     * This specific instance handles the request for notification permissions,
     * triggering a toast message if the user denies the request.
     */
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                } else {
                    Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    /**
     * Creates a notification channel for an specific category of notifications
     * @param name name for channel
     * @param description context for the channel
     */
    protected void createNotificationChannel(String name, String description) {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NOTIFICATION_PRIORITY);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    /** Request for permission to send notifications
     *
     */
    protected void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    /**
     * Sends specific notification based on a code
     * @param code states the type of notification to be sent
     */
    protected void sendNotification(int code) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setColor(ContextCompat.getColor(this, R.color.white))
                .setPriority(NotificationCompat.PRIORITY_HIGH);


                if(code == 1){
                    builder.setAutoCancel(true);
                    builder.setContentTitle("Meta conseguida");
                    builder.setContentText("¡Felicidades! Has llegado a tu meta de hidratación.");
                }if(code == 2){
                    builder.setSilent(true);
                    builder.setOngoing(true);
                    builder.setContentTitle("Mochila no conectada");
                    builder.setContentText("No se ha encontrado una conexion con la mochila, conectala");
                }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED ||
                Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
    /**
     * Process the data received by the bluetooth connection (if not null)
     * If capacity received differs from stored value, updates stored
     * If totalDrunk received is greater than stored value, updates stored
     * @param dataStream data received
     */
    protected void processWaterData(WaterData dataStream){
        if(dataStream != null){
            if(dataStream.capacity != capacity){
                capacity = dataStream.capacity;
            }
            if(dataStream.totalDrank > totalDrank){
                totalDrank = dataStream.totalDrank;
            }
            isBottlePlaced = dataStream.isBottlePlaced;
            hoursSinceDrink = dataStream.hoursSinceDrink;
        }else{
            sendNotification(2);
        }
    }
    protected void processWaterData(WaterData dataStream,boolean isFaking){
         double alterValue = 0.1 + (Math.random() * (0.5 - 0.1));
        if(dataStream != null){
            isBottlePlaced = dataStream.isBottlePlaced;
            if(!isBottlePlaced){
                capacity -= alterValue;
                totalDrank += alterValue;
            }
            hoursSinceDrink = dataStream.hoursSinceDrink;
        }else{
            sendNotification(2);
        }
    }
}
