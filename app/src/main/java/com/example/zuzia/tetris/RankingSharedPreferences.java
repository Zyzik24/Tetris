package com.example.zuzia.tetris;

import android.content.Context;

public class RankingSharedPreferences {

    private RankingPreferences tabPreferences[]=new RankingPreferences[10];
    private RankingPreferences recentScorePreferences;

    public RankingSharedPreferences(Context context)
    {
        for(int key=0; key<10; key++)
        {
            tabPreferences[key]=new RankingPreferences(""+key,context);
        }
        recentScorePreferences =new RankingPreferences("recentKey",context);
    }

    public void putPreferences(int score)
    {
        for(int key=0; key<10; key++)
        {
            if(score>tabPreferences[key].getPreferences(0))
            {
                recentScorePreferences.putPreferences(key);
                int previousScore=tabPreferences[key].getPreferences(0);
                for (int i=key+1; i<10; i++)
                {
                    int pScore=tabPreferences[i].getPreferences(0);
                    tabPreferences[i].putPreferences(previousScore);
                    previousScore=pScore;
                }
                tabPreferences[key].putPreferences(score);
                return;
            }
        }
    }

    public int getPreferences(int key)
    {
        return tabPreferences[key].getPreferences(0);
    }

    public int getRecentScorePreferences()
    {
        return recentScorePreferences.getPreferences(-1);
    }
}
