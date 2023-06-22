package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class room2 extends AppCompatActivity {
    FirebaseDatabase firebase=FirebaseDatabase.getInstance();
    DatabaseReference led1Ref = firebase.getReference("/light/led2");
    DatabaseReference fan1Ref = firebase.getReference("/light/fan1");

    private static String MY_PREFSLIGHT2= "switch_prefslight2";
    private static String SWITCH_STATUSLIGHT2= "switch_statuslight2";
    boolean switch_statuslight2;
    SharedPreferences mypreferencelight2;
    SharedPreferences.Editor myeditorlight2;
    private static String MY_PREFSFAN= "switch_prefsfan";
    private static String SWITCH_STATUSFAN= "switch_statusfan";
    boolean switch_statusfan;
    SharedPreferences mypreferencefan;
    SharedPreferences.Editor myeditorfan;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);

        Switch lightswitch2=(Switch) findViewById(R.id.r2l1);
        Switch fanswitch=(Switch) findViewById(R.id.r2l2);



        mypreferencelight2= getSharedPreferences(MY_PREFSLIGHT2,MODE_PRIVATE);
        myeditorlight2= getSharedPreferences(MY_PREFSLIGHT2,MODE_PRIVATE).edit();
        switch_statuslight2= mypreferencelight2.getBoolean(SWITCH_STATUSLIGHT2, false);
        lightswitch2.setChecked(switch_statuslight2);
        lightswitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(lightswitch2.isChecked())
                {
                    myeditorlight2.putBoolean(SWITCH_STATUSLIGHT2, true);
                    myeditorlight2.apply();
                    led1Ref.setValue(1);
                    Toast.makeText(room2.this, "light on", Toast.LENGTH_SHORT).show();
                    lightswitch2.setChecked(true);
                }
                else
                {
                    myeditorlight2.putBoolean(SWITCH_STATUSLIGHT2, false);
                    myeditorlight2.apply();
                    led1Ref.setValue(0);
                    Toast.makeText(room2.this, "light off", Toast.LENGTH_SHORT).show();
                    lightswitch2.setChecked(false);
                }
            }
        });
        mypreferencefan= getSharedPreferences(MY_PREFSFAN,MODE_PRIVATE);
        myeditorfan= getSharedPreferences(MY_PREFSFAN,MODE_PRIVATE).edit();
        switch_statusfan= mypreferencefan.getBoolean(SWITCH_STATUSFAN, false);
        fanswitch.setChecked(switch_statusfan);
        fanswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (fanswitch.isChecked())
                {
                    myeditorfan.putBoolean(SWITCH_STATUSFAN, true);
                    myeditorfan.apply();
                    fan1Ref.setValue(1);
                    Toast.makeText(room2.this, "Fan on", Toast.LENGTH_SHORT).show();
                    fanswitch.setChecked(true);

                }

                else
                {
                    myeditorfan.putBoolean(SWITCH_STATUSFAN, false);
                    myeditorfan.apply();
                    fan1Ref.setValue(0);
                    Toast.makeText(room2.this, "fan off", Toast.LENGTH_SHORT).show();
                    fanswitch.setChecked(false);
                }
            }
        });
    }
}
