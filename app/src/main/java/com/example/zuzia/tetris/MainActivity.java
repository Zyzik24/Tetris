package com.example.zuzia.tetris;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private GestureDetectorCompat detector;
    private Tetris tetris;
    private Button blockNextMain;
    private Button blockNext1;
    private Button blockNext2;
    private Button blockNext3;
    private Figure figure;
    private int initialBlockDiff=27;
    private Runnable runnable;
    private final Handler handler = new Handler();
    private boolean isSpeedUp;
    private int howMove;//ilość perzesunieć przy połowicznym przesuwie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blockNextMain=(Button)findViewById(R.id.blockNextMain);
        blockNext1=(Button)findViewById(R.id.blockNext1);
        blockNext2=(Button)findViewById(R.id.blockNext2);
        blockNext3=(Button)findViewById(R.id.blockNext3);
        tetris= (Tetris)getFragmentManager().findFragmentById(R.id.tetris);
        detector=new GestureDetectorCompat(this,this);
        figure= new Figure(blockNextMain, blockNext1, blockNext2, blockNext3);
        tetris.textScore=(TextView)findViewById(R.id.textScore);
        tetris.textLvl=(TextView)findViewById(R.id.textLvl);
        tetris.layoutMainActivity=(RelativeLayout)findViewById(R.id.layoutMainActivity);
        howMove=0;
        isSpeedUp=false;
        figure.randomNumber();
        figure.moveFigure(initialBlockDiff);
        moveHorizontal();

    }

    private void moveHorizontal()
    {
        runnable=new Runnable() {
            @Override
            public void run() {
                if(tetris.fallBlocks())//czy może spaść i czy nieprzesuniete w bok
                {
                    if(!isSpeedUp)//do częściowego przyspieszenia
                    {
                        tetris.moveHorizontalFall();
                        handler.postDelayed(this, tetris.speed);
                    }
                    else
                    {
                        howMove++;
                        tetris.moveHorizontalFall();
                        if(howMove==3)
                        {
                            howMove=0;
                            isSpeedUp=false;
                        }
                        handler.postDelayed(this, 0);
                    }
                }
                else
                {
                    tetris.moveHorizontalNotFall();
                    tetris.figure.number=figure.number;
                    tetris.figure.moveFigure(37);
                    figure.randomNumber();
                    figure.moveFigure(initialBlockDiff);
                    handler.postDelayed(this,0);
                }
            }
        };
        handler.post(runnable);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        tetris.changeRotation();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        tetris.moveFigureVertical(e1,e2);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(tetris.speedUp(e1,e2)==true)
        {
            handler.removeCallbacks(runnable);
            isSpeedUp=true;
            moveHorizontal();
        }
        return true;
    }

}

