package com.example.zuzia.tetris;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MoveButton {

    private MainActivity mainActivity;
    private Runnable runnablePress;//do przesuwania figur przyciskami
    private final Handler handlerPress = new Handler();

    MoveButton(final MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;

        final ImageButton buttonRight = (ImageButton) mainActivity.findViewById(R.id.buttonRight);
        buttonTouch(buttonRight, 100, R.drawable.righta, R.drawable.rightb);

        final ImageButton buttonLeft=(ImageButton) mainActivity.findViewById(R.id.buttonLeft);
        buttonTouch(buttonLeft, -100, R.drawable.lefta, R.drawable.leftb);

        final ImageButton buttonRote = (ImageButton) mainActivity.findViewById(R.id.buttonRote);
        buttonRote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mainActivity.tetris.changeRotation();
                        buttonRote.setImageResource(R.drawable.roteb);
                        return true;
                    case MotionEvent.ACTION_UP:
                        buttonRote.setImageResource(R.drawable.rotea);
                        return true;
                }
                return false;
            }
        });


        final ImageButton buttonDown= (ImageButton)mainActivity.findViewById(R.id.buttonDown);
        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(!mainActivity.tetris.pause)
                        {
                            mainActivity.handler.removeCallbacks(mainActivity.runnable);
                            mainActivity.tetris.speed=0;
                            mainActivity.moveHorizontal();
                        }
                        buttonDown.setImageResource(R.drawable.downb);
                        return true;
                    case MotionEvent.ACTION_UP:
                        buttonDown.setImageResource(R.drawable.downa);
                        return true;
                }

                return false;
            }
        });

    }

    private void buttonTouch (final ImageButton button, final int distance, final int drawa, final int drawb)
    {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonMoveTime(distance);
                        button.setImageResource(drawb);
                        return true;
                    case MotionEvent.ACTION_UP:
                        handlerPress.removeCallbacks(runnablePress);
                        button.setImageResource(drawa);
                        return true;
                }
                return false;
            }
        });
    }

    private void buttonMoveTime(final int distance)
    {
        //do przciskw przesuwających figure w prawo i w lewo
        runnablePress=new Runnable() {
            @Override
            public void run() {
                mainActivity.tetris.moveFigureVertical(null,null, distance);
                handlerPress.postDelayed(this, 200);
            }
        };
        handlerPress.post(runnablePress);
    }
}
