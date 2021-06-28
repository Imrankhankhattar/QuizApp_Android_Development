package com.imrankhan.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.imrankhan.quizapp.R;
import com.imrankhan.quizapp.adaptors.quiz_adopter;
import com.imrankhan.quizapp.models.question;
import com.imrankhan.quizapp.models.quiz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public FirebaseFirestore firestore;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;
    public  Adapter quizadopter;
    public Context context;
    public RecyclerView rv;
    public NavigationView navigationView;
    public ArrayList<quiz> quizlist=new ArrayList<quiz>();
    public quiz_adopter qz=new quiz_adopter(this,quizlist);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.appBar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        rv=findViewById(R.id.recyclevw);
        navigationView=findViewById(R.id.navigation);
        //setting drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               // int id = item.getItemId();
                Log.i("DRAWER", item.getTitle().toString());
                String id =item.getTitle().toString();
                String pid="Profile";
                if( id.equals(pid)) {
                    Intent intent = new Intent(MainActivity.this, profileActivity.class);
                    MainActivity.this.startActivity(intent);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
       // addData();
        //setting recycler view
        GridLayoutManager gm=new GridLayoutManager(this,2);
        rv.setAdapter(qz);
        rv.setLayoutManager(gm);
       setupviews();

    }

    private void setupviews() {
            setupfirebase();
            setupdatepicker();
    }

    private void setupdatepicker() {
        FloatingActionButton datepicker;
        datepicker=findViewById(R.id.datepicker);
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().build();
                datePicker.show(getSupportFragmentManager(),"DatePicker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        String pattern = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        String date = simpleDateFormat.format(selection);
                        Log.d("DatePicker1", date);
                        Intent intent = new Intent(MainActivity.this, questionActivity.class);
                        intent.putExtra("DATE", date);
                        startActivity(intent);

                    }
                });
                datePicker.addOnNegativeButtonClickListener(v1 -> {
                    Log.d("DATE2", datePicker.getHeaderText());
//                    Log.d("DATE", datePicker.getSelection().toString());

                });
                datePicker.addOnCancelListener(dialog -> {
                    Log.d("DATE", "Date Picker cancelled");
                });
            }
        });

    }

    private void setupfirebase() {
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Quizzes").addSnapshotListener((value, error) -> {
            if(value != null){
                quizlist.clear();
                quizlist.addAll(value.toObjects(quiz.class));
                Log.d("IMRAN", value.toObjects(quiz.class).toString());
                qz.notifyDataSetChanged();
            }
            if(value == null || error != null){
                Toast.makeText(this, "Cannot retrieve Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void addData()
    {
        quiz q=new quiz("12","12");
        quiz q1=new quiz("12","12");
        quiz q2=new quiz("12","12");
        quiz q3=new quiz("12","12");
        quizlist.add(q);
        quizlist.add(q1);
        quizlist.add(q2);
        quizlist.add(q3);
    }

}