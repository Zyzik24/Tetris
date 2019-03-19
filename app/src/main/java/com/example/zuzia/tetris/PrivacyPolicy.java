package com.example.zuzia.tetris;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class PrivacyPolicy {

    WebView web;
    Activity activity;

    public PrivacyPolicy(){}

    public PrivacyPolicy(Activity activity)
    {
        this.activity = activity;
    }

    public void buildDialogPrivacyPolicy()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View mView= activity.getLayoutInflater().inflate(R.layout.activity_privacy_policy, null);
        builder.setView(mView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        final WebView web =(WebView)dialog.findViewById(R.id.webView);
        final Button buttonOk = (Button) dialog.findViewById(R.id.buttonAgree);


        web.loadUrl("file:///android_asset/privacy_policy.html");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
