package com.example.zuzia.tetris;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

public class Controls {

    private Activity activity;
    private SharedPreferences preferencesGestures;
    private SharedPreferences preferencesButtons;


    public Controls(Activity activity, Context context) {
        this.activity = activity;
        preferencesGestures = context.getSharedPreferences("gestures", Activity.MODE_PRIVATE);
        preferencesButtons = context.getSharedPreferences("buttons", Activity.MODE_PRIVATE);
    }

    public void buildDialogControls()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View mView= activity.getLayoutInflater().inflate(R.layout.controls_layout, null);
        builder.setView(mView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        final Button buttonGestures = (Button) dialog.findViewById(R.id.buttonGestures);
        final Button buttonButtons = (Button) dialog.findViewById(R.id.buttonButtons);
        Button buttonOk = (Button) dialog.findViewById(R.id.buttonOK);

        buttonGestures.setActivated(getGestures());
        buttonButtons.setActivated(getButtons());

        buttonGestures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!v.isActivated())
                {
                    v.setActivated(true);
                    setGestures(true);
                }
                else
                {
                    if(!getButtons())
                    {
                        buttonButtons.setActivated(true);
                        setButtons(true);

                    }
                    v.setActivated(false);
                    setGestures(false);
                }
            }
        });

        buttonButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!v.isActivated())
                {
                    v.setActivated(true);
                    setButtons(true);
                }
                else
                {
                    if(!getGestures())
                    {
                        buttonGestures.setActivated(true);
                        setGestures(true);
                    }
                    v.setActivated(false);
                    setButtons(false);

                }
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public boolean getGestures()
    {
        return preferencesGestures.getBoolean("gestures",true);
    }

    public boolean getButtons()
    {
        return preferencesButtons.getBoolean("buttons",true);
    }

    private void setGestures(boolean sign)
    {
        SharedPreferences.Editor editor = preferencesGestures.edit();
        editor.putBoolean("gestures", sign);
        editor.commit();
    }

    private void setButtons(boolean sign)
    {
        SharedPreferences.Editor editor = preferencesButtons.edit();
        editor.putBoolean("buttons", sign);
        editor.commit();
    }

}