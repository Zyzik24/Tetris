package com.example.zuzia.tetris;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends AlertDialog{

    protected DialogActivity(Context context) {
        super(context);
    }

    public void showDialog(String dialogText, final Dialog dialogActions, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View mview= getLayoutInflater().inflate(R.layout.dialog_layout,null );
        builder.setView(mview);
        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView text= (TextView)dialog.findViewById(R.id.text);
        text.setText(dialogText);
        Button buttonYes = (Button) dialog.findViewById(R.id.buttonYes);
        Button buttonNo = (Button) dialog.findViewById(R.id.buttonNo);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActions.yes(dialog);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActions.no(dialog);
            }
        });
    }
}
