package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Email,password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email=findViewById(R.id.email_login_id);
        password=findViewById(R.id.password_login_id);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void registration_Now(View view) {
        Intent intent=new Intent(MainActivity.this,Registration_form.class);
        startActivity(intent);
    }

    public void Login(View view) {
        String email=Email.getText().toString().trim();
        String pass=password.getText().toString().trim();


            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Login Successfull....", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Main_screen.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Login Failed....." + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }
}