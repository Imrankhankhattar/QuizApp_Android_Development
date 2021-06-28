package com.imrankhan.quizapp.adaptors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.imrankhan.quizapp.R;
import com.imrankhan.quizapp.activities.MainActivity;
import com.imrankhan.quizapp.activities.login_activity;
import com.imrankhan.quizapp.activities.questionActivity;
import com.imrankhan.quizapp.models.quiz;
import com.imrankhan.quizapp.utils.iconPicker;
import com.imrankhan.quizapp.utils.colourPicker;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.imrankhan.quizapp.R.id.quizitem;
import static com.imrankhan.quizapp.R.id.textView;

public class quiz_adopter extends RecyclerView.Adapter<quiz_adopter.quizviewholder> {
    private Context context;
    private ArrayList<quiz> quizArrayList=new ArrayList<quiz>();

    public quiz_adopter(Context context, ArrayList<quiz> quizlist) {
        this.context=context;
        this.quizArrayList=quizlist;
    }

    @NonNull
    @Override
    public quizviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.quiz_items,parent,false);
        return new quizviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull quizviewholder holder, int position) {
       // TextView title=holder.title;
        //holder.title.setText(title.getText());
        String quiz=quizArrayList.get(position).title;
        holder.title.setText(quiz+"");
        //setting colour and icon
        colourPicker colourPicker=new colourPicker();
        iconPicker iconPicker=new iconPicker();
        holder.card.setCardBackgroundColor(Color.parseColor(colourPicker.getColor()));
        holder.img.setImageResource(iconPicker.getIcon());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ERROR", "IMRAN KHAN");
                Toast.makeText(context, quizArrayList.get(position).title, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, questionActivity.class);
                intent.putExtra("DATE", quizArrayList.get(position).title);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }

    public class quizviewholder extends ViewHolder{
        private ImageView img;
        private TextView title;
        private CardView card;
        public quizviewholder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.quizicon);
            title=itemView.findViewById(R.id.quiztitle);
            card=itemView.findViewById(R.id.cardcontainer);
        }
    }
}
