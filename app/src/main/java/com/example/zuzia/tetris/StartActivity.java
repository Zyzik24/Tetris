package com.example.zuzia.tetris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;


public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        StartAppSDK.init(this, "209010497", false);
        StartAppAd.disableSplash();

    }

    public void playClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void rankingClick(View view) {
        Intent intent = new Intent(this,RankingActivity.class);
        startActivity(intent);
    }


    public void ControlsClick(View view) {
        Controls controls = new Controls(this,this);
        controls.buildDialogControls();
    }

    public void privacyPolicyClick(View view)
    {
            PrivacyPolicy privacyPolicy = new PrivacyPolicy(this);
            privacyPolicy.buildDialogPrivacyPolicy();

    }
}
