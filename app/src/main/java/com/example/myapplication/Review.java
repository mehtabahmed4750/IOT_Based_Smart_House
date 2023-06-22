package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Review extends AppCompatActivity {

    private EditText emailEditText;
    private EditText reviewEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        emailEditText = findViewById(R.id.email_edit_text);
        reviewEditText = findViewById(R.id.review_edit_text);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String review = reviewEditText.getText().toString().trim();

                if (!email.isEmpty() && !review.isEmpty()) {
                    sendEmail(email, review);
                } else {
                    Toast.makeText(Review.this, "Please enter your email and review", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmail(String email, String review) {
        String recipientEmail = "iotsmarthouse6@gmail.com";
        String subject = "New Review";
        String body = "Email: " + email + "\nReview: " + review;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}
