package com.imrankhan.quizapp.models;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class quiz  implements Serializable{
    public String id="";
    public String title="";
    //public ArrayList<question> quizlist=new ArrayList<question>();
    public HashMap<String ,question> question=new HashMap<>();
    public quiz(){ }
    public quiz(String id, String title) {
        this.id = id;
        this.title = title;
           }

    @Override
    public String toString() {
        return "quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", quizlist=" + question +
                '}';
    }

    public HashMap<String, com.imrankhan.quizapp.models.question> getQuestion() {
        return question;
    }

    public void setQuestion(HashMap<String, com.imrankhan.quizapp.models.question> question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
