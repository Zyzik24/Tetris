package com.example.zuzia.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Drawer extends SurfaceView implements SurfaceHolder.Callback {

    ArrayList<DrawerSquare> punkty;
    Paint paint = new Paint();
    public int initialBlockDiff;

    public Drawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        punkty = new ArrayList<DrawerSquare>();
        paint = new Paint();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void drawBlock(DrawerSquare blockDraw)
    {
        punkty.add(blockDraw);
    }

    private boolean isWin(int i)
    {
        if(i==10)
            return true;
        return false;
    }

    private void clearWin(DrawerSquare blockDraw)
    {
        for (DrawerSquare punkt : punkty) {
            if(blockDraw.blockDraw.top>punkt.blockDraw.top)
            {
                punkt.blockDraw.top=punkt.blockDraw.top+initialBlockDiff;
                punkt.blockDraw.bottom=punkt.blockDraw.bottom+initialBlockDiff;
            }
        }
    }

    public void DrawBlockDown(DrawerSquare blockDraw, TextView textScore,ColorBackground colorBackground)
    {
        DrawerSquare tab[] = new DrawerSquare[11];
        int i=0;
        for (DrawerSquare punkt : punkty) {
            if(blockDraw.blockDraw.top==punkt.blockDraw.top)
            {
                tab[i]=punkt;
                i++;
            }
            if (isWin(i))
                break;
        }
        if(isWin(i))
        {
            for(int j=0; j<11; j++)
                punkty.remove(tab[j]);
            clearWin(blockDraw);
            int score=Integer.parseInt(textScore.getText().toString())+50;
            textScore.setText(score+"");
            if(score%1000==0)
            {
                int lvl=Integer.parseInt(colorBackground.textLvl.getText().toString())+1;
                colorBackground.textLvl.setText(lvl+"");
                colorBackground.moveGradient();
            }
        }
        invalidate();
    }

    public boolean isAboveDrawBlock(Button block)
    {
        //sprawdza czy dół buttona łączy się z górą, któregoś z rysunków
        for (DrawerSquare punkt : punkty) {
            if(block.getY()+initialBlockDiff==punkt.blockDraw.top && block.getX()==punkt.blockDraw.left)
                return true;
        }
        return false;
    }

    public boolean isRightDrawBlock(Button block)
    {
        //sprawdza czy prawy buttona łączy się z lewym, któregoś z rysunków
        for (DrawerSquare punkt : punkty) {
            if(block.getX()+initialBlockDiff==punkt.blockDraw.left && block.getY()==punkt.blockDraw.top)
                return true;
        }
        return false;
    }

    public boolean isLeftDrawBlock(Button block)
    {
        //sprawdza czy lewy buttona łączy się z prawym, któregoś z rysunków
        for (DrawerSquare punkt : punkty) {
            if(block.getX()==punkt.blockDraw.right+2 && block.getY()==punkt.blockDraw.top)
                return true;
        }
        return false;
    }

    public void clearlist()
    {
        punkty.clear();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {

        for (DrawerSquare punkt : punkty) {
            paint.setColor(punkt.color);
            canvas.drawRect(punkt.blockDraw, paint);
        }

    }

}
