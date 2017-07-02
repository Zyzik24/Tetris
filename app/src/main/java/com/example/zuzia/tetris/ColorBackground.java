package com.example.zuzia.tetris;


import android.widget.RelativeLayout;
import android.widget.TextView;

public class ColorBackground {

    public TextView textLvl;
    private Drawer drawer;
    private RelativeLayout layoutMainActivity;

    public ColorBackground(TextView textLvl, Drawer drawer,RelativeLayout layoutMainActivity)
    {
        this.textLvl=textLvl;
        this.drawer=drawer;
        this.layoutMainActivity=layoutMainActivity;
    }

    public void moveColor()
    {
        int lvl= Integer.parseInt(textLvl.getText().toString())%20;
        int gradient = R.drawable.background1;
        switch (lvl)
        {
            case 0:
                gradient=R.drawable.background20;
                break;

            case 1:
                gradient=R.drawable.background1;
                break;

            case 2:
                gradient=R.drawable.background2;
                break;

            case 3:
                gradient=R.drawable.background3;
                break;

            case 4:
                gradient=R.drawable.background4;
                break;

            case 5:
                gradient=R.drawable.background5;
                break;

            case 6:
                gradient=R.drawable.background6;
                break;

            case 7:
                gradient=R.drawable.background7;
                break;

            case 8:
                gradient=R.drawable.background8;
                break;

            case 9:
                gradient=R.drawable.background9;
                break;

            case 10:
                gradient=R.drawable.background10;
                break;

            case 11:
                gradient=R.drawable.background11;
                break;

            case 12:
                gradient=R.drawable.background12;
                break;

            case 13:
                gradient=R.drawable.background13;
                break;

            case 14:
                gradient=R.drawable.background14;
                break;

            case 15:
                gradient=R.drawable.background15;
                break;

            case 16:
                gradient=R.drawable.background16;
                break;

            case 17:
                gradient=R.drawable.background17;
                break;

            case 18:
                gradient=R.drawable.background18;
                break;

            case 19:
                gradient=R.drawable.background19;
                break;

        }

        layoutMainActivity.setBackgroundResource(gradient);
        drawer.setBackgroundResource(gradient);
    }

}
