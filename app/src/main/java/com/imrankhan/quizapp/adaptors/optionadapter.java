package com.imrankhan.quizapp.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imrankhan.quizapp.R;
import com.imrankhan.quizapp.models.question;

import java.util.ArrayList;
import android.widget.Toast;

public class optionadapter extends RecyclerView.Adapter<optionadapter.optionviewholder> {
    public Context context;
    public ArrayList<String> options = new ArrayList<>();
    public question Question=new question();
    public String[] option={Question.option1,Question.option2,Question.option3,Question.option4};

    public optionadapter(question que) {
        this.options.add(que.option1);
        this.options.add(que.option2);
        this.options.add(que.option3);
        this.options.add(que.option4);
        this.Question = que;
    }
    @NonNull
    @Override
    public optionviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.option_design,parent,false);
        return new optionviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull optionviewholder holder, int position) {
       // TextView option=holder.option;
        //holder.option.setText(option.getText());
        String question_option = options.get(position);
        holder.option.setText(question_option+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question.userans=options.get(position);
                Log.d("OPTION", Question.userans);
                notifyDataSetChanged();

            }
        });
        if(Question.getUserans() == question_option){
            holder.itemView.setBackgroundResource(R.drawable.selected_option_bg);
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.normal_option_bg);
        }
    }

    @Override
    public int getItemCount() {
        return option.length;
    }

    public class optionviewholder extends RecyclerView.ViewHolder {
        private TextView option;
        public optionviewholder(@NonNull View itemView) {
            super(itemView);
            option=itemView.findViewById(R.id.quizoption);
        }
    }

}
