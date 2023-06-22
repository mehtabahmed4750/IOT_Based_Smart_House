package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore fbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();

        EditText e1=(EditText) findViewById(R.id.etsName);
        EditText e2=(EditText) findViewById(R.id.etsEmail);
        EditText e3=(EditText) findViewById(R.id.etsMobile);
        EditText e4=(EditText) findViewById(R.id.etsPassword);
        EditText e5=(EditText) findViewById(R.id.etsCPassword);
        TextView t1=(TextView) findViewById(R.id.tvLogin);
        Button b1=(Button) findViewById(R.id.btnSignup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String email=e2.getText().toString();
                String mobileno=e3.getText().toString();
                String pass=e4.getText().toString();
                String confpass=e5.getText().toString();


                if (TextUtils.isEmpty(name))
                {
                    e1.setError("Enter Name");
                }
                else if (TextUtils.isEmpty(email))
                {
                    e2.setError("Enter Email");
                }
                else if (TextUtils.isEmpty(mobileno))
                {
                    e3.setError("Enter Mobile Number");
                }
                else if (TextUtils.isEmpty(pass))
                {
                    e4.setError("Enter Password");
                }
                else if (TextUtils.isEmpty(confpass))
                {
                    e5.setError("Enter Confirm Password");
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                            {
                                @Override
                                public void onSuccess(AuthResult authResult)
                                {
                                    Intent i=new Intent(signup.this, login.class);
                                    startActivity(i);


                                    fbs.collection("user")
                                            .document(auth.getUid())
                                            .set(new UserModel(name,email,mobileno,pass,confpass));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this, login.class);
                startActivity(i);
            }
        });


    }
}