package com.example.zuzia.tetris;

import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ColorBackground {

    public TextView textLvl;
    private Drawer drawer;

    public ColorBackground(TextView textLvl, Drawer drawer)
    {
        this.textLvl=textLvl;
        this.drawer=drawer;
    }

    private int getCenterColor()
    {
        int lvl = Integer.parseInt(textLvl.getText().toString()) % 20;
            switch (lvl)
        {
            case 0:
                return 0xff9d88af;

            case 1:
                return 0xff5D5D5F;

            case 2:
                return 0xffbfbffa;

            case 3:
                return  0xffa4e0fa;

            case 4:
                return 0xffbbf9d8;

            case 5:
                return 0xfff7ddad;

            case 6:
                return 0xffedaca9;

            case 7:
                return 0xfffaf9b7;

            case 8:
                return 0xffffffff;

            case 9:
                return 0xffb6abac;

            case 10:
                return 0xfff9ddfa;

            case 11:
                return 0xfff5a573;

            case 12:
                return 0xff818c84;

            case 13:
                return 0xffefe0df;

            case 14:
                return 0xff8d8db6;

            case 15:
                return 0xffe9bffa;

            case 16:
                return 0xff66969b;

            case 17:
                return 0xffbeb58a;

            case 18:
                return 0xffe1e1e1;

            case 19:
                return 0xffce8e93;
        }
        return 0xFF5D5D5F;
    }

    public void moveGradient() {
        int[] color = {0xFF37373E, getCenterColor(), 0xFF000000};
        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, color);
        drawer.setBackgroundDrawable(gradient);
    }

}


