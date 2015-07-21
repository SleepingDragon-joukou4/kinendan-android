package com.sleepingdragon.joko4nen.nosmoke.syohin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        buttonseni();
    }

    protected void onResume() {
        super.onResume();
        //UserIDとTeamIDを取得
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        String UserID = Savedata.getString("UserID", "なし");
        String TeamID = Savedata.getString("TeamID", "なし");
        //sample用のUserID
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    if(result==null){onError();return;}
                    double ModerationPrace2=0;
                    JSONObject ja=result.getJSONObject(0);
                    if(!ja.has("response")){
                        String ModerationPrice = ja.getString("ModerationPrice");
                        ModerationPrace2 = Double.valueOf(ModerationPrice);
                    }

                    TextView textView = (TextView) findViewById(R.id.kingaku);
                    textView.setText(String.valueOf((int)ModerationPrace2));


                    if(ModerationPrace2<=0){
                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s0);



                    }else if(ModerationPrace2>0&&ModerationPrace2<=500){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s0_500);

                    }else if(ModerationPrace2>500&&ModerationPrace2<=1000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s500_1000);

                    }else if(ModerationPrace2>1000&&ModerationPrace2<=2000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s1000_2000);

                    }else if(ModerationPrace2>2000&&ModerationPrace2<=3000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s2000_3000);

                    }else if(ModerationPrace2>3000&&ModerationPrace2<=5000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s3000_5000);

                    }else if(ModerationPrace2>5000&&ModerationPrace2<=7000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s5000_7000);

                    }else if(ModerationPrace2>7000&&ModerationPrace2<=10000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s7000_10000);

                    }else if(ModerationPrace2>10000){
                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.s10000);

                    }
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


