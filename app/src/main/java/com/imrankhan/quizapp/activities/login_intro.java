package com.imrankhan.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imrankhan.quizapp.R;

import android.content.Intent;
import android.widget.Toast;

public class login_intro extends AppCompatActivity {
    private Button getstarted;
    private FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);
        getstarted=findViewById(R.id.btngetstarted);
        firebaseAuth = FirebaseAuth.getInstance();
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_intro.this, login_activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            Toast.makeText(login_intro.this,"User Already Logged in",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(login_intro.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}