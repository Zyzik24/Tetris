package com.example.zuzia.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;


public class RankingActivity extends Activity {

    private RankingSharedPreferences rankingSharedPreferences;
    private String tabRanking[]= new  String[10];
    private ListView listRanking;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        StartAppSDK.init(this, "209010497", false);
        StartAppAd.disableSplash();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        listRanking = (ListView) findViewById(R.id.listRanking);
        rankingSharedPreferences=new RankingSharedPreferences(this);

        for (int i=0; i<10; i++)
        {
            int j=i+1;
            tabRanking[i]=j+". "+rankingSharedPreferences.getPreferences(i);
        }

        ArrayList<String> rankL = new ArrayList<String>();
        rankL.addAll( Arrays.asList(tabRanking) );

        adapter = new ArrayAdapter<String>(this, R.layout.list_layout, rankL)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(R.id.Row);

                if(rankingSharedPreferences.getRecentScorePreferences()!=-1 )
                {
                    if (position==rankingSharedPreferences.getRecentScorePreferences())
                    {
                        text.setTextColor(0xFFD67A77);
                    }
                    else
                    {
                        text.setTextColor(0xFFDCD9D9);
                    }
                }

                return view;
            }
        };

        listRanking.setEnabled(false);
        listRanking.setAdapter(adapter);
    }

    public void backClick(View view) {
        finish();
    }
}

