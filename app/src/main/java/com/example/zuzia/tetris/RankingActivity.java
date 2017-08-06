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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class RankingActivity extends Activity {

    private RankingSharedPreferences rankingSharedPreferences;
    private String tabRanking[]= new  String[10];
    private ListView listRanking;
    private ArrayAdapter<String> adapter;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        MobileAds.initialize(this, "ca-app-pub-7178724899379030~6121622001");

        mAdView = (AdView) findViewById(R.id.adView3);
//        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-7178724899379030~6121622001").build();
        mAdView.loadAd(adRequest);

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

