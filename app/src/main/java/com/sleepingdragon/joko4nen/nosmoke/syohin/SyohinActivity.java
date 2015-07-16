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
        //String UserID = Savedata.getString("UserID", "なし");
        String TeamID = Savedata.getString("TeamID", "なし");
        //sample用のUserID
        String UserID = "User20150528s4KV2d";
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    JSONObject ja=result.getJSONObject(0);
                    String ModerationPrice = ja.getString("ModerationPrice");
                          //TextViewにModerationPricを挿入
                    TextView textView = (TextView) findViewById(R.id.kingaku);
                    textView.setText(ModerationPrice);

                    double ModerationPrace2 = Double.valueOf(ModerationPrice);

                if(ModerationPrace2<=0){
                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.zeroen);



                    }else if(ModerationPrace2>0&&ModerationPrace2<=100){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.kinako);

                    }else if(ModerationPrace2>100&&ModerationPrace2<=1000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.beer1000);

                    }else if(ModerationPrace2>1000&&ModerationPrace2<=3000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.eiga3000);

                    }else if(ModerationPrace2>3000&&ModerationPrace2<=5000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.yakiniku5000);

                    }else if(ModerationPrace2>5000&&ModerationPrace2<=7500){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.soft7500);

                    }else if(ModerationPrace2>7500&&ModerationPrace2<=10000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.kaisen10000);

                    }else if(ModerationPrace2>10000&&ModerationPrace2<=12500){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.itunes12500);

                    }else if(ModerationPrace2>12500&&ModerationPrace2<=15000){
                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.mediaplayer15000);

                    }else if(ModerationPrace2>15000&&ModerationPrace2<=20000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.psp20000);

                    }else if(ModerationPrace2>20000&&ModerationPrace2<=25000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.tokei25000);

                    }else if(ModerationPrace2>25000&&ModerationPrace2<=30000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.tv30000);

                    }else if(ModerationPrace2>30000&&ModerationPrace2<=50000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.pc50000);

                    }else if(ModerationPrace2>50000&&ModerationPrace2<=100000){

                    ImageView imageView1 = (ImageView)findViewById(R.id.ImageView);
                    imageView1.setImageResource(R.drawable.bed100000);

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


