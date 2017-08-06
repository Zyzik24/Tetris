package com.example.zuzia.tetris;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class OptionsButton{

    private MainActivity mainActivity;

    OptionsButton(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
    }

    public void pauseClick(View view) {
        pause(view);
    }


    public void ghostClick(View view) {
        if(view.isActivated())
        {
            view.setActivated(false);
            mainActivity.tetris.ghostClass.ghostBlocksInVisible();
            mainActivity.tetris.ghost=false;
        }
        else
        {
            view.setActivated(true);
            mainActivity.tetris.ghostClass.ghostBlocksVisible();
            mainActivity.tetris.ghostBlockMoveHorizontal();
            mainActivity.tetris.ghost=true;
        }
    }

    public void restartClick() {
        restartGame();;
    }

    public void rankingClick() {
        Intent intent = new Intent(mainActivity,RankingActivity.class);
        mainActivity.startActivity(intent);
    }

    private void restart()
    {
        mainActivity.handler.removeCallbacks(mainActivity.runnable);
        mainActivity.figure.randomNumber();
        mainActivity.figure.moveFigure(mainActivity.initialBlockDiff);
        mainActivity.tetris.restartGame();
        Button buttonPause = (Button) mainActivity.findViewById(R.id.buttonPause);
        buttonPause.setActivated(true);
        pause(buttonPause);
    }

    private void pause(View view)
    {
        if(view.isActivated())
        {
            view.setActivated(false);
            mainActivity.moveHorizontal();
            mainActivity.tetris.pause =false;
        }
        else
        {
            view.setActivated(true);
            mainActivity.handler.removeCallbacks(mainActivity.runnable);
            mainActivity.tetris.pause =true;
        }
    }

    private void restartGame()
    {
        DialogActivity dialogActivity= new DialogActivity(mainActivity);
        dialogActivity.showDialog("Do you want to restart the game?",new Dialog() {
                    @Override
                    public void yes(android.app.AlertDialog dialog) {
                        mainActivity.putPreferences();
                        restart();
                        dialog.dismiss();
                    }

                    @Override
                    public void no(android.app.AlertDialog dialog) {
                        if(mainActivity.tetris.lose)
                        {
                            mainActivity.finish();
                        }
                        else
                        {
                            dialog.dismiss();
                        }
                    }
                },
                mainActivity
        );
    }

    public void gameOver()
    {
        restartGame();
    }

    public void exit()
    {
        DialogActivity dialogActivity= new DialogActivity(mainActivity);
        dialogActivity.showDialog("Do you want to quit?",new Dialog() {
                    @Override
                    public void yes(android.app.AlertDialog dialog) {
                        mainActivity.finish();
                    }

                    @Override
                    public void no(android.app.AlertDialog dialog) {
                        dialog.dismiss();
                    }
                },
                mainActivity);
    }
}
