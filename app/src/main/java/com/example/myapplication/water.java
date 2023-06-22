package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class water extends AppCompatActivity {
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference waterLevelRef = firebase.getReference("/Sensor/depth");
    DatabaseReference waterdistanceRef = firebase.getReference("/Sensor/distanceInInch");

    TextView waterLevelTextView;
    ImageView waterLevelImageView;
    EditText maxWaterLevelEditText;
    Button okButton;

    int percentage = 0;
    int maxWaterLevel = 0;

    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "WaterPrefs";
    private static final String KEY_MAX_WATER_LEVEL = "maxWaterLevel";
    private static final String KEY_PERCENTAGE = "percentage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        waterLevelTextView = findViewById(R.id.tv0);
        waterLevelImageView = findViewById(R.id.imgData);
        maxWaterLevelEditText = findViewById(R.id.et0);
        okButton = findViewById(R.id.ok_btn);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        maxWaterLevel = sharedPreferences.getInt(KEY_MAX_WATER_LEVEL, 0);
        percentage = sharedPreferences.getInt(KEY_PERCENTAGE, 0);

        maxWaterLevelEditText.setText(String.valueOf(maxWaterLevel));
        waterLevelTextView.setText(percentage + " %");
        setWaterLevelImageAndText();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maxWaterLevelStr = maxWaterLevelEditText.getText().toString();
                maxWaterLevel = Integer.parseInt(maxWaterLevelStr);

                waterLevelRef.setValue(maxWaterLevel);
                waterdistanceRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int currentDepthInInches = dataSnapshot.getValue(Integer.class);
                        int currentWaterLevel = maxWaterLevel - currentDepthInInches;
                        percentage = (currentWaterLevel * 100) / maxWaterLevel;

                        waterLevelTextView.setText(percentage + " %");
                        setWaterLevelImageAndText();

                        // Store the values in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(KEY_MAX_WATER_LEVEL, maxWaterLevel);
                        editor.putInt(KEY_PERCENTAGE, percentage);
                        editor.apply();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("WaterActivity", "Failed to read water level: " + databaseError.getMessage());
                    }
                });
            }
        });
    }

    private void setWaterLevelImageAndText() {
        if (percentage < 0) {
            percentage = 0;
        } else if (percentage > 100) {
            percentage = 100;
        }

        waterLevelTextView.setText(percentage + " %");


        if (percentage >= 95 && percentage <= 100) {
            waterLevelImageView.setImageResource(R.drawable.i100);
        } else if (percentage >= 90 && percentage < 95) {
            waterLevelImageView.setImageResource(R.drawable.i95);
        } else if (percentage >= 80 && percentage < 90) {
            waterLevelImageView.setImageResource(R.drawable.i90);
        } else if (percentage >= 70 && percentage < 80) {
            waterLevelImageView.setImageResource(R.drawable.i80);
        } else if (percentage >= 55 && percentage < 70) {
            waterLevelImageView.setImageResource(R.drawable.i70);
        } else if (percentage >= 50 && percentage < 55) {
            waterLevelImageView.setImageResource(R.drawable.i55);
        } else if (percentage >= 40 && percentage < 50) {
            waterLevelImageView.setImageResource(R.drawable.i50);
        } else if (percentage >= 30 && percentage < 40) {
            waterLevelImageView.setImageResource(R.drawable.i40);
        } else if (percentage >= 20 && percentage < 30) {
            waterLevelImageView.setImageResource(R.drawable.i30);
        } else if (percentage >= 15 && percentage < 20) {
            waterLevelImageView.setImageResource(R.drawable.i20);
        } else if (percentage >= 10 && percentage < 15) {
            waterLevelImageView.setImageResource(R.drawable.i15);
        } else if (percentage > 8 && percentage < 10) {
            waterLevelImageView.setImageResource(R.drawable.i10);
        }else if (percentage > 6 && percentage <= 8) {
            waterLevelImageView.setImageResource(R.drawable.i8);
        }else if (percentage > 4 && percentage <= 6) {
            waterLevelImageView.setImageResource(R.drawable.i6);
        }else if (percentage > 2 && percentage <= 4) {
            waterLevelImageView.setImageResource(R.drawable.i4);
        }else if (percentage > 0 && percentage <= 2) {
            waterLevelImageView.setImageResource(R.drawable.i2);
        }
        else {
            waterLevelImageView.setImageResource(R.drawable.i0);
        }
    }

}

