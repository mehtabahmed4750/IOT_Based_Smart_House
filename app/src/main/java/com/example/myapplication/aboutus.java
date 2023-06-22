package com.example.myapplication;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class aboutus extends AppCompatActivity {

    TextView emailTextView;
    TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);


        emailTextView = findViewById(R.id.email_text);
        phoneTextView = findViewById(R.id.phone_text);

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        phoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber();
            }
        });

    }
        private void sendEmail () {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:iotsmarthouse6@gmail.com"));  // Replace with your company's email address
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Request");

            String[] recipients = {"iotsmarthouse6@gmail.com"};  // Replace with your company's email address

            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_TEXT, "Please enter your message here");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(intent, "Send Email"));
            } else {
                Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
            }
        }

        private void dialPhoneNumber () {
            String phoneNumber = "+923165444073";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
