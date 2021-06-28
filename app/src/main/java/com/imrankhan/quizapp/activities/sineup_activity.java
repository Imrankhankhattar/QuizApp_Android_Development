package com.imrankhan.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.imrankhan.quizapp.R;

public class sineup_activity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText password2;
    private Button register;
    private FirebaseAuth auth;
    private TextView redirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sineup_activity);
        email=findViewById(R.id.txtEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        password2=findViewById(R.id.editTextTextPassword2);
        register=findViewById(R.id.sinebutton);
        redirect=findViewById(R.id.redlogin);
        auth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtxt=email.getText().toString();
                String passtxt=password.getText().toString();
                String passtxt2=password2.getText().toString();
                if(TextUtils.isEmpty(emailtxt)||TextUtils.isEmpty(passtxt)||TextUtils.isEmpty(passtxt2)){
                    Toast.makeText(sineup_activity.this,"Empty credentials",Toast.LENGTH_SHORT).show();
                    Log.i("IMRAN", "onClick: yes");
                }
                else if(!(TextUtils.equals(passtxt,passtxt2))){
                    Toast.makeText(sineup_activity.this,"Password does not match",Toast.LENGTH_LONG).show();
                }
                else if(passtxt.length()<6){
                    Toast.makeText(sineup_activity.this,"Password is too short",Toast.LENGTH_LONG).show();
                }
                else{
                    sineupuser(emailtxt,passtxt);
                }
            }
        });
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), login_activity.class);
                v.getContext().startActivity(intent);
                finish();
            }
        });
    }

    private void sineupuser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(sineup_activity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(sineup_activity.this,"User Registered Succesfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(sineup_activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(sineup_activity.this,"User Already Registered",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}