package com.example.zuzia.tetris;

import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SizeView {

    private int width;
    private int height;
    private RelativeLayout relativeLayout;
    private RelativeLayout layoutTetris;
    public int initialBlockDiff;
    private MainActivity mainActivity;

    SizeView(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height=displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
        relativeLayout=(RelativeLayout) mainActivity.findViewById(R.id.relativeLayout);
        layoutTetris=(RelativeLayout) mainActivity.findViewById(R.id.layoutTetris);
        initialBlockDiff=getInitialBlockDiff();
        moveSize();
    }

    private int getInitialBlockDiff()
    {
        int w=width-width/3;
        String widthChar = ""+w;
        String widthString="";

        for(int i=0; i<widthChar.length()-1; i++)
        {
            widthString=widthString+widthChar.charAt(i);
        }
        w=Integer.parseInt(widthString+"0");

        int h=height-(height/5);
        String heightChar = ""+h;
        String heightString="";
        for(int i=0; i<heightChar.length()-2; i++)
        {
            heightString=heightString+heightChar.charAt(i);
        }

        int z=Integer.parseInt(""+heightChar.charAt(heightChar.length()-2));
        if(z%2!=0)
        {
            z--;
        }
        h=Integer.parseInt(heightString+z+"0");

        if((w/10)<(h/20))
            return w/10;
        else
            return h/20;

    }

    private void moveSize()
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width-12, 20*initialBlockDiff+12 );
        relativeLayout.setLayoutParams(params);
        params = new RelativeLayout.LayoutParams(initialBlockDiff*10-1, initialBlockDiff*20-1);
        layoutTetris.setLayoutParams(params);
        setImageButton();
    }

    public void sizeblocks (Button blockMain, Button block1, Button block2, Button block3)
    {
        sizeblock(blockMain);
        sizeblock(block1);
        sizeblock(block2);
        sizeblock(block3);
    }

    private void sizeblock(Button block)
    {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(initialBlockDiff-2, initialBlockDiff-2 );
        block.setLayoutParams(params);
    }

    private void setImageButton()
    {
        ImageButton buttonLeft = (ImageButton) mainActivity.findViewById(R.id.buttonLeft);
        ImageButton buttonRight = (ImageButton) mainActivity.findViewById(R.id.buttonRight);
        ImageButton buttonDown = (ImageButton) mainActivity.findViewById(R.id.buttonDown);
        ImageButton buttonRote = (ImageButton) mainActivity.findViewById(R.id.buttonRote);
        int buttonWidth = (width-12)/4;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(buttonWidth,height/9);
        RelativeLayout.LayoutParams paramsDown = new RelativeLayout.LayoutParams(buttonWidth-buttonWidth/5,height/9);
        buttonLeft.setLayoutParams(params);
        buttonRight.setLayoutParams(params);
        buttonDown.setLayoutParams(paramsDown);
        buttonRote.setLayoutParams(params);
        buttonLeft.setX(0);
        buttonRight.setX(buttonWidth);
        buttonDown.setX(3*buttonWidth+buttonWidth/5);
        buttonRote.setX(2*buttonWidth);
    }

}

