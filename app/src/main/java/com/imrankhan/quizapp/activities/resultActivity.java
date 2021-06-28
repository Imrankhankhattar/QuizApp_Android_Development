package com.imrankhan.quizapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.imrankhan.quizapp.R;
import com.imrankhan.quizapp.models.question;
import com.imrankhan.quizapp.models.quiz;

import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class resultActivity extends AppCompatActivity {
    quiz Quiz = new quiz();
    Map<String, question> questions = Map.of();
    TextView textscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textscore = (TextView) findViewById(R.id.txtScore);
        setupviews();
    }

    private void setupviews() {
        //String  quizdata=getIntent().getStringExtra("QUIZ");
        //Quiz= new Gson().fromJson(quizdata,quiz.class);
        String quizData = getIntent().getStringExtra("QUIZ");
        Gson gson = new Gson();
        quiz[] Quiz = gson.fromJson(quizData, quiz[].class);
        questions = Quiz[0].question;
        calculatescore();
        setanwser();
    }

    private void setanwser() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        TextView ans;
        ans = findViewById(R.id.txtAnswer);
        for (int i = 0; i < questions.size(); i++) {
            question Question = questions.get("question" + index);
            index++;
            stringBuilder.append("<font color '#18206f'><b>Question<br/>" + Question.description + "</b></font><br/><br/>");
            stringBuilder.append("<font color '#18206f'>Answer<br/>" + Question.answer + "</font><br/><br/><br/>");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ans.setText(Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            ans.setText(Html.fromHtml(stringBuilder.toString()));
        }

    }

    private void calculatescore() {
        int score = 0;
        int index = 1;
        for (int i = 0; i < questions.size(); i++) {
            question Question = questions.get("question" + index);
            Log.i("GETING", questions.get("question" + index).toString());
            index++;
            if(Question.getAnswer().equals(Question.getUserans())){
                score += 10;
                Log.i("GETING", score+"");
            }
        }
        textscore.setText("Your Score: "+score);
    }
}
