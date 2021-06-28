package com.imrankhan.quizapp.utils;

import java.lang.reflect.Array;
import java.util.Random;

import java.util.List;

public class colourPicker {
    String[] color = {"#ff0000", "#ff0f00", "#ffff00", "#FFBB86FC", "#FF018786"};
    int col;

    public String getColor() {
        Random rand = new Random();
        col = rand.nextInt(color.length - 1);
        return color[col];
    }
}
