package com.example.zuzia.tetris;

import android.widget.Button;
import java.util.Random;

public class Figure {

    private Button blockMain;
    private Button block1;
    private Button block2;
    private Button block3;
    public int color;
    public boolean rotable;
    public int number;

    Figure(Button blockMain, Button block1, Button block2, Button block3)
    {
        this.blockMain=blockMain;
        this.block1=block1;
        this.block2=block2;
        this.block3=block3;
        rotable=true;
    }

    public void moveFigure(int initialBlockDiff)
    {
        switch (number)
        {
            case 1:
                // kwadracik
                block1.setX(blockMain.getX()+initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX());
                block2.setY(blockMain.getY()+initialBlockDiff);
                block3.setX(blockMain.getX()+initialBlockDiff);
                block3.setY(blockMain.getY()+initialBlockDiff);
                setColorBlocks(0xff05dec9);
                rotable=false;
                break;
            case 2:
                //linia
                block1.setX(blockMain.getX()-initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX()+initialBlockDiff);
                block2.setY(blockMain.getY());
                block3.setX(blockMain.getX()+2*initialBlockDiff);
                block3.setY(blockMain.getY());
                setColorBlocks(0xffdf1313);
                rotable=true;
                break;
            case 3:
                //plus bez kawałka
                block1.setX(blockMain.getX()-initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX()+initialBlockDiff);
                block2.setY(blockMain.getY());
                block3.setX(blockMain.getX());
                block3.setY(blockMain.getY()+initialBlockDiff);
                setColorBlocks(0xfff08d0c);
                rotable=true;
                break;
            case 4:
                //l z ząbkiem po prawej
                block1.setX(blockMain.getX()-initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX()+initialBlockDiff);
                block2.setY(blockMain.getY());
                block3.setX(blockMain.getX()+initialBlockDiff);
                block3.setY(blockMain.getY()+initialBlockDiff);
                setColorBlocks(0xfff9ed0d);
                rotable=true;
                break;
            case 5:
                //l z ząbkiem po lewej
                block1.setX(blockMain.getX()-initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX()+initialBlockDiff);
                block2.setY(blockMain.getY());
                block3.setX(blockMain.getX()-initialBlockDiff);
                block3.setY(blockMain.getY()+initialBlockDiff);
                setColorBlocks(0xffa1f70c);
                rotable=true;
                break;
            case 6:
                //z w prawą
                block1.setX(blockMain.getX()+initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX()-initialBlockDiff);
                block2.setY(blockMain.getY()+initialBlockDiff);
                block3.setX(blockMain.getX());
                block3.setY(blockMain.getY()+initialBlockDiff);
                setColorBlocks(0xff0c3bf7);
                rotable=true;
                break;
            case 7:
                //z w lewą
                block1.setX(blockMain.getX()-initialBlockDiff);
                block1.setY(blockMain.getY());
                block2.setX(blockMain.getX());
                block2.setY(blockMain.getY()+initialBlockDiff);
                block3.setX(blockMain.getX()+initialBlockDiff);
                block3.setY(blockMain.getY()+initialBlockDiff);
                setColorBlocks(0xfff200ff);
                rotable=true;
                break;
        }
    }

    public void randomNumber()
    {
        Random rand = new Random();
        number=rand.nextInt(7)+1;
    }

    private void setColorBlocks(int color)
    {
        this.color=color;
        blockMain.setBackgroundColor(color);
        block1.setBackgroundColor(color);
        block2.setBackgroundColor(color);
        block3.setBackgroundColor(color);
    }
}
