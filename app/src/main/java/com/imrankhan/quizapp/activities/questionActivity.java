package com.imrankhan.quizapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.collect.ImmutableList;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imrankhan.quizapp.R;
import com.imrankhan.quizapp.adaptors.optionadapter;
import com.imrankhan.quizapp.adaptors.quiz_adopter;
import com.imrankhan.quizapp.models.question;
import com.imrankhan.quizapp.models.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class questionActivity<Question> extends AppCompatActivity {
    public RecyclerView recyclerView;
    public FirebaseFirestore firestore;
    //public List<quiz> quizzes;
    public ArrayList<quiz> quizzes = new ArrayList<>();
    public Map<String, question> questions=Map.of();
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setUpFireStore();
        setupEventlistners();
    }

    private void setupEventlistners() {
        Button btnNext;
        Button btnPrev;
        Button btnFinish;

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrevious);
        btnFinish = findViewById(R.id.btnSubmit);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btnprev", index+"");
                index = index - 1;
                bindview();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btnNext", index+"");
                index = index + 1;
                bindview();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("QUE", questions + "");
                Intent intent = new Intent(questionActivity.this,resultActivity.class);
//                Gson gson1=new Gson();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json=gson.toJson(quizzes);
                String extra=gson.toJson(quizzes);
                intent.putExtra("QUIZ",extra);
                startActivity(intent);
                finish();

            }
        });

    }

    private void setupfirebase() {
        firestore = FirebaseFirestore.getInstance();
        String date = new Intent().getStringExtra("DATE");
        if (date != null) {
            firestore.collection("Quizzes").whereEqualTo("title", date)
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> mylist = snapshot.getDocuments();

                            Log.i("IAMSAIF", "setUpFireStore: " + mylist.get(0).getData());
                            quizzes.addAll(snapshot.toObjects(quiz.class));
                            questions = quizzes.get(0).question;
                            //Log.i("DATEEE", "setUpFireStore: " + snapshot.getDocuments());
                            bindview();
                        }
                    });
        }
    }

    private void setUpFireStore() {

        firestore = FirebaseFirestore.getInstance();
        String date = getIntent().getStringExtra("DATE");
        if (date != null) {
            firestore.collection("Quizzes")
                    .whereEqualTo("title", date)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                                quizzes.addAll(queryDocumentSnapshots.toObjects(quiz.class));
                                Log.i("IMRANKHAN", "onSuccess: " + quizzes);
                                questions = quizzes.get(0).question;
                                bindview();
                            } else {
                                Toast.makeText(questionActivity.this, "No Quiz for Given Date", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(questionActivity.this, MainActivity.class);
                                questionActivity.this.startActivity(intent);
                                finish();
                            }
                        }
                    });
        }

    }

    private void bindview() {
        Button btnNext;
        Button btnPrev;
        Button btnFinish;

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrevious);
        btnFinish = findViewById(R.id.btnSubmit);

        btnNext.setVisibility(View.GONE);
        btnPrev.setVisibility(View.GONE);
        btnFinish.setVisibility(View.GONE);

        if (index == 1) {
            btnNext.setVisibility(View.VISIBLE);
        }
        else if (index == questions.size() || questions.size() == 1) {
            btnFinish.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        }
        else
            {
            btnNext.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.VISIBLE);

        }
        question Question = questions.get("question" + index);
//        question Question = new question();
//        Question.description = " Who Am i ?";
//        Question.option1 = "IMRAN";
//        Question.option2 = "ALI";
//        Question.option3 = "NOMAN";
//        Question.option4 = "QAARI SAHB";
//        Question.answer="A";
        if (Question != null) {
            TextView descriptionofQue = findViewById(R.id.description);
            descriptionofQue.setText(Question.description);
            //setting up adopter and rv
            recyclerView = findViewById(R.id.optionList);
            optionadapter Optionadapter = new optionadapter(Question);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(Optionadapter);
        }
    }


}