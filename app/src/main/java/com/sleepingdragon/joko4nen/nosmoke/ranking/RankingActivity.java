package com.sleepingdragon.joko4nen.nosmoke.ranking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ryosei on 2015/06/17.
 */
public class RankingActivity extends Activity{
    String UserID;
    String TeamID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        Button HomeButton = (Button) findViewById(R.id.HomeButton);
        Button RankingButton = (Button) findViewById(R.id.RankingButton);
        Button ScheduleButton = (Button) findViewById(R.id.ScheduleButton);
        Button SyohinButton = (Button) findViewById(R.id.SyohinButton);
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        TeamID = Savedata.getString("TeamID", "なし");
        UserID = Savedata.getString("UserID", "なし");


        //HomeButtonが押された場合Home画面に遷移
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        //RankingButtonが押された場合Ranking画面に遷移
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        RankingActivity.class);
                startActivity(intent);
            }
        });
        //ScheduleButtonが押された場合schedule画面に遷移
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //SyohinButtonが押された場合syohin画面に遷移
        SyohinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        SyohinActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onResume(){
        super.onResume();
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    Log.d("", result.toString());
                    LinearLayout li =(LinearLayout)RankingActivity.this.findViewById(R.id.ranking_bodyline);
                    li.removeAllViews();
                    for(int i=0;i<result.length();i++) {
                        JSONObject ja = result.getJSONObject(i);
                        String UserName=ja.getString("UserName");
                        String PercentComplete=ja.getString("PercentComplete");
                        View rankingview = getLayoutInflater().inflate(R.layout.ranking_view, null);
                        li.addView(rankingview);
                        TextView rankrankv = (TextView) rankingview.findViewById(R.id.rank_rank);
                        rankrankv.setText(((int)i+1)+"");
                        TextView ranknamev = (TextView)rankingview.findViewById(R.id.rank_name);
                        ranknamev.setText(UserName);
                        TextView rankrituv = (TextView)rankingview.findViewById(R.id.rank_ritu);
                        rankrituv.setText(PercentComplete+"%");
                    }


                }catch (JSONException e) {
                    e.printStackTrace();
                }
                return;
            }
        };
        //executeで非同期処理開始
        URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=rankingselect"+
        "&UserId="+UserID+"&TeamId="+TeamID);


    }

}
