package com.sleepingdragon.joko4nen.nosmoke.schedule;

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
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ryosei on 2015/06/18.
 */
public class ScheduleActivity extends Activity {
    String TeamID;
    String UserID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        Button HomeButton = (Button) findViewById(R.id.HomeButton);
        Button RankingButton = (Button) findViewById(R.id.RankingButton);
        Button ScheduleButton = (Button) findViewById(R.id.ScheduleButton);
        Button SyohinButton = (Button) findViewById(R.id.SyohinButton);
        //HomeButtonが押された場合Home画面に遷移
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        //RankingButtonが押された場合Ranking画面に遷移
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this,
                        RankingActivity.class);
                startActivity(intent);
            }
        });
        //ScheduleButtonが押された場合schedule画面に遷移
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this,
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //SyohinButtonが押された場合syohin画面に遷移
        SyohinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this,
                        SyohinActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        TeamID = Savedata.getString("TeamID", "なし");
        UserID = Savedata.getString("UserID", "なし");
    }
    @Override
    protected void onResume(){
        super.onResume();
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
            @Override
            protected void onPostExecute(JSONArray result) {
                super.onPostExecute(result);
                if(result==null)return;
                try {
                    Log.d("", result.toString());
                    LinearLayout li =(LinearLayout)ScheduleActivity.this.findViewById(R.id.schedule_data);
                    li.removeAllViews();
                    for(int i=0;i<result.length();i++) {
                        JSONObject ja = result.getJSONObject(i);
                        String getd=ja.getString("Date");
                        String shOn=ja.getString("smokinghistoryObjectiveNumber");
                        String shPn=ja.getString("smokinghistoryPerformanceNumber");
                        View scheduleview = getLayoutInflater().inflate(R.layout.scheduleview, null);
                        li.addView(scheduleview);
                        TextView scheduledatev = (TextView) scheduleview.findViewById(R.id.schedule_date_v);
                        scheduledatev.setText(getd);
                        TextView scheduleobjectivev = (TextView)scheduleview.findViewById(R.id.schedule_objective_v);
                        scheduleobjectivev.setText(shOn);
                        TextView scheduleperformancev = (TextView)scheduleview.findViewById(R.id.schedule_performance_v);
                        scheduleperformancev.setText(shPn);
                    }


                }catch (JSONException e) {
                    e.printStackTrace();
                }
                return;
            }
        };
        //executeで非同期処理開始
        URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=scheduleselect" +
                "&UserId=" + UserID + "&TeamId=" + TeamID);


    }
}
