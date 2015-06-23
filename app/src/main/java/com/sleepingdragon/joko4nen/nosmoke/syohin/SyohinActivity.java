package com.sleepingdragon.joko4nen.nosmoke.syohin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.regist;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ryosei on 2015/06/19.
 */
public class SyohinActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syohin);
    }

    protected void onResume() {
        super.onResume();
        //UserIDとTeamIDを取得
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        //String UserID = Savedata.getString("UserID", "なし");
        String TeamID = Savedata.getString("TeamID", "なし");
        //sample用のUserID
        String UserID = "User20150528s4KV2d";
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    JSONObject ja=result.getJSONObject(0);
                    String ModerationPrice = ja.getString("ModerationPrice");
                          //TextViewにModerationPricを挿入
                    TextView textView = (TextView) findViewById(R.id.kingaku);
                    textView.setText(ModerationPrice);
                    

                }catch (JSONException e) {
                    e.printStackTrace();
                }
                return;

            }
        };
        //executeで非同期処理開始
        URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=smokingselect" +
                "&UserId="+UserID+"&TeamId="+TeamID);
    }

        public void buttonseni() {
        Button HomeButton = (Button) findViewById(R.id.HomeButton);
        Button RankingButton = (Button) findViewById(R.id.RankingButton);
        Button ScheduleButton = (Button) findViewById(R.id.ScheduleButton);
        Button SyohinButton = (Button) findViewById(R.id.SyohinButton);


        //HomeButtonが押された場合Home画面に遷移
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SyohinActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        //RankingButtonが押された場合Ranking画面に遷移
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SyohinActivity.this,
                        RankingActivity.class);
                startActivity(intent);
            }
        });
        //ScheduleButtonが押された場合schedule画面に遷移
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SyohinActivity.this,
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //SyohinButtonが押された場合syohin画面に遷移
        SyohinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SyohinActivity.this,
                        SyohinActivity.class);
                startActivity(intent);
            }
        });
    }
}


