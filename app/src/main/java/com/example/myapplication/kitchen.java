package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
public class kitchen extends AppCompatActivity {
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();

    DatabaseReference fireRef = firebase.getReference("/light/fire");
    DatabaseReference gasRef = firebase.getReference("/Sensor/gasdetector");
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        Switch fireSwitch = findViewById(R.id.k2);
        Switch gasSwitch = findViewById(R.id.k3);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        sharedPreferences = getSharedPreferences("SwitchState", MODE_PRIVATE);


        fireSwitch.setChecked(sharedPreferences.getBoolean("fireState", false));
        gasSwitch.setChecked(sharedPreferences.getBoolean("gasState", false));



        fireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@Nonnull DataSnapshot dataSnapshot) {
                Boolean isFireDetected = dataSnapshot.getValue(Boolean.class);
                if (isFireDetected != null && isFireDetected) {
                    NotificationCompat.Builder builder= new NotificationCompat.Builder(kitchen.this, "My Notification");
                    builder.setContentTitle("Fire Alert");
                    builder.setContentText("Fire detected in the kitchen");
                    builder.setSmallIcon(R.drawable.fire_icon);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat= NotificationManagerCompat.from(kitchen.this);
                    managerCompat.notify(1, builder.build());
                    fireSwitch.setChecked(true);
                } else {
                    fireSwitch.setChecked(false);
                }
                sharedPreferences.edit().putBoolean("fireState", fireSwitch.isChecked()).apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled event
            }
        });

        gasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@Nonnull DataSnapshot dataSnapshot) {
                Integer gasValue = dataSnapshot.getValue(Integer.class);
                if ( gasValue > 2) {
                    NotificationCompat.Builder builder= new NotificationCompat.Builder(kitchen.this, "My Notification");
                    builder.setContentTitle("Gas Alert");
                    builder.setContentText("Gas leakage detected in the kitchen");
                    builder.setSmallIcon(R.drawable.gas_icon);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat= NotificationManagerCompat.from(kitchen.this);
                    managerCompat.notify(1, builder.build());
                    gasSwitch.setChecked(true);
                } else {
                    gasSwitch.setChecked(false);
                }
                sharedPreferences.edit().putBoolean("gasState", gasSwitch.isChecked()).apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled event
            }
        });
    }
}