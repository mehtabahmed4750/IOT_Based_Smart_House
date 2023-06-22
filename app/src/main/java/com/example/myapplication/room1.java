package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class room1 extends AppCompatActivity {
    FirebaseDatabase firebase=FirebaseDatabase.getInstance();
    DatabaseReference led1Ref = firebase.getReference("/light/led1");
    DatabaseReference door1Ref = firebase.getReference("/light/door1");
    private static String MY_PREFSLIGHT= "switch_prefslight";
    private static String SWITCH_STATUSLIGHT= "switch_statuslight";
    boolean switch_statuslight;
    SharedPreferences mypreferencelight;
    SharedPreferences.Editor myeditorlight;
    private static String MY_PREFSDOOR= "switch_prefsdoor";
    private static String SWITCH_STATUSDOOR= "switch_statusdoor";
    boolean switch_statusdoor;
    SharedPreferences mypreferencedoor;
    SharedPreferences.Editor myeditordoor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        Switch lightswitch=(Switch) findViewById(R.id.r1l1);
        Switch doorswitch=(Switch) findViewById(R.id.r1l2);

        mypreferencelight= getSharedPreferences(MY_PREFSLIGHT,MODE_PRIVATE);
        myeditorlight= getSharedPreferences(MY_PREFSLIGHT,MODE_PRIVATE).edit();
        switch_statuslight= mypreferencelight.getBoolean(SWITCH_STATUSLIGHT, false);
        lightswitch.setChecked(switch_statuslight);

        lightswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(lightswitch.isChecked())
                {
                    myeditorlight.putBoolean(SWITCH_STATUSLIGHT, true);
                    myeditorlight.apply();
                    led1Ref.setValue(1);
                    Toast.makeText(room1.this, "light on", Toast.LENGTH_SHORT).show();
                    lightswitch.setChecked(true);
                }
                else
                {
                    myeditorlight.putBoolean(SWITCH_STATUSLIGHT, false);
                    myeditorlight.apply();
                    led1Ref.setValue(0);
                    Toast.makeText(room1.this, "light off", Toast.LENGTH_SHORT).show();
                    lightswitch.setChecked(false);
                }
            }
        });
        mypreferencedoor= getSharedPreferences(MY_PREFSDOOR,MODE_PRIVATE);
        myeditordoor= getSharedPreferences(MY_PREFSDOOR,MODE_PRIVATE).edit();
        switch_statusdoor= mypreferencedoor.getBoolean(SWITCH_STATUSDOOR, false);
        doorswitch.setChecked(switch_statusdoor);
        doorswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(doorswitch.isChecked())
                {
                    myeditordoor.putBoolean(SWITCH_STATUSDOOR, true);
                    myeditordoor.apply();
                    door1Ref.setValue(1);
                    Toast.makeText(room1.this, "Door unlocked", Toast.LENGTH_SHORT).show();
                    doorswitch.setChecked(true);
                }
                else
                {
                    myeditordoor.putBoolean(SWITCH_STATUSDOOR, false);
                    myeditordoor.apply();
                    door1Ref.setValue(0);
                    Toast.makeText(room1.this, "Door locked", Toast.LENGTH_SHORT).show();
                    doorswitch.setChecked(false);

                }
            }
        });
    }
}