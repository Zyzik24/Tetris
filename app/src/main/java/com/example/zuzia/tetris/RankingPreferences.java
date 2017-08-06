package com.example.zuzia.tetris;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class RankingPreferences {

    private SharedPreferences preferences;
    private String key;

    public RankingPreferences(String key, Context context)
    {
        this.key=key;
        preferences = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
    }

    public void putPreferences(int score)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, score);
        editor.commit();
    }

    public int getPreferences(int help)
    {
        return preferences.getInt(key,help);
    }

}
