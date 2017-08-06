package com.example.zuzia.tetris;

import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat detector;
    public Tetris tetris;
    private Button blockNextMain;
    private Button blockNext1;
    private Button blockNext2;
    private Button blockNext3;
    public Figure figure;
    public int initialBlockDiff=27;
    public Runnable runnable;
    public final Handler handler = new Handler();
    private boolean isSpeedUp;
    private int howMove;//ilość perzesunieć przy połowicznym przesuwie

    private OptionsButton optionsButton;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-7178724899379030~6121622001");

        mAdView = (AdView) findViewById(R.id.adView2);
//        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-7178724899379030~6121622001").build();
        mAdView.loadAd(adRequest);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        tetris= (Tetris)getFragmentManager().findFragmentById(R.id.tetris);
        SizeView sizeView = new SizeView(displayMetrics.widthPixels, displayMetrics.heightPixels, (RelativeLayout)findViewById(R.id.relativeLayout), (RelativeLayout)findViewById(R.id.layoutTetris));
        tetris.sizeView=sizeView;

        MoveButton moveButton = new MoveButton(this);
        optionsButton = new OptionsButton(this);

        blockNextMain=(Button)findViewById(R.id.blockNextMain);
        blockNext1=(Button)findViewById(R.id.blockNext1);
        blockNext2=(Button)findViewById(R.id.blockNext2);
        blockNext3=(Button)findViewById(R.id.blockNext3);
        detector=new GestureDetectorCompat(this,this);
        figure= new Figure(blockNextMain, blockNext1, blockNext2, blockNext3);
        tetris.textScore=(TextView)findViewById(R.id.textScore);
        tetris.textLvl=(TextView)findViewById(R.id.textLvl);
        howMove=0;
        isSpeedUp=false;
        figure.randomNumber();
        figure.moveFigure(initialBlockDiff);
        moveHorizontal();
    }

    public void moveHorizontal()
    {
        runnable=new Runnable() {
            @Override
            public void run() {
                if(!tetris.lose)
                {
                    if(tetris.fallBlocks())//czy może spaść i czy nieprzesuniete w bok
                    {
                        if(!isSpeedUp)//do częściowego przyspieszenia
                        {
                            tetris.moveHorizontalFall();
                            handler.postDelayed(this, tetris.speed);
                        }
                        else {
                            howMove++;
                            tetris.moveHorizontalFall();
                            if (howMove == 3) {
                                howMove = 0;
                                isSpeedUp = false;
                            }
                            handler.postDelayed(this, 0);
                        }
                    }
                    else
                    {
                        tetris.moveHorizontalNotFall();
                        tetris.figure.number=figure.number;
                        tetris.number=tetris.figure.number;//do zapisu potrzebny
                        tetris.figure.moveFigure(tetris.initialBlockDiff);
                        if (tetris.ghost)//jeżeli był właączony duszek to ma być, ale nowy, bo figura się zmieniła
                        {
                            tetris.ghostBlockMoveHorizontal();
                        }
                        figure.randomNumber();
                        figure.moveFigure(initialBlockDiff);
                        if (!tetris.fallBlocks())
                            tetris.lose =true;
                        handler.postDelayed(this,0);
                    }
                }
                else
                {
                    optionsButton.gameOver();
                }
            }
        };
        handler.post(runnable);
    }


    public void putPreferences()
    {
        RankingSharedPreferences rankingSharedPreferences = new RankingSharedPreferences(this);
        rankingSharedPreferences.putPreferences(Integer.parseInt(tetris.textScore.getText().toString()));
    }

    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        handler.removeCallbacks(runnable);
        if(!tetris.pause)
        {
            moveHorizontal();
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        putPreferences();
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
        tetris.moveFigureVertical(e1,e2, e2.getX()-e1.getX());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(tetris.speedUp(e1,e2) && !tetris.pause)
        {
            handler.removeCallbacks(runnable);
            isSpeedUp=true;
            moveHorizontal();
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        optionsButton.exit();
    }

    public void pauseClick(View view) {
        optionsButton.pauseClick(view);
    }

    public void ghostClick(View view) {
        optionsButton.ghostClick(view);
    }

    public void restartClick(View view) {
        optionsButton.restartClick();
    }

    public void rankingClick(View view) {
        optionsButton.rankingClick();
    }

}

