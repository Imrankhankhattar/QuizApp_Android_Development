package com.imrankhan.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.imrankhan.quizapp.R;


public class profileActivity extends AppCompatActivity {
    public FirebaseAuth auth;
    TextView email;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email = findViewById(R.id.txtEmail);
        logout=findViewById(R.id.btnLogout);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            email.setText(auth.getCurrentUser().getEmail());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getInstance().signOut();
                Intent intent = new Intent(v.getContext(), login_activity.class);
                v.getContext().startActivity(intent);
                finish();
            }
        });
    }
}