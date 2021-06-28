package com.imrankhan.quizapp.utils;

import com.imrankhan.quizapp.R;

import java.util.Random;

public class iconPicker {
    int[] color = {R.drawable.ic_quiz1,R.drawable.ic_quiz2,R.drawable.ic_quiz3,R.drawable.ic_quiz4};
    int col;

    public int getIcon() {
        Random rand = new Random();
        col = rand.nextInt(color.length - 1);
        return color[col];
    }
}

