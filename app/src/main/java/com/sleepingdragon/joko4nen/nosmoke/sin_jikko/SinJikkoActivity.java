package com.sleepingdragon.joko4nen.nosmoke.sin_jikko;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ryosei on 2015/06/23.
 */
public class SinJikkoActivity extends Activity {
    ArrayList<String> name;
    ArrayList<String> punishmentnumber;
    ArrayList<String> nowpunishmentnumber;
    private ImageView image;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sin_jikko);
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // 戻るボタンの無効化
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }
    @Override
    protected void onResume() {
        super.onResume();

            Intent i=getIntent();
            name=i.getStringArrayListExtra("NameList");
            punishmentnumber=i.getStringArrayListExtra("PunishmentNumberList");
            nowpunishmentnumber=i.getStringArrayListExtra("NowPunishmentNumberList");
            if(name!=null) {
                //sin_text1にTextViewにName(目標本数を超えたユーザの名前)を挿入
                TextView sin_text1 = (TextView) findViewById(R.id.sin_text1);
                sin_text1.setText(name.get(0));
            }

        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        String TeamID = Savedata.getString("TeamID", "なし");
        String UserID = Savedata.getString("UserID", "なし");
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this) {
        @Override
        protected void onPostExecute(JSONArray result) {
            try {
                //罰ゲーム内容を取得
                JSONObject ja = result.getJSONObject(0);
                String Punishment = ja.getString("Punishment");
                //TextView judgement にjudgement（罰ゲーム内容）を挿入
                TextView judgement = (TextView) findViewById(R.id.judgement);
                judgement.setText(Punishment);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;

        }
    };
    //executeで非同期処理開始
    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=punishmentselect" +
            "&UserId=" + UserID + "&TeamId=" + TeamID);


        // 「確認」buttonでhome画面に遷移
        Button createteam = (Button) findViewById(R.id.regsuccess_next);
        createteam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(name!=null){
                    name.remove(0);
                    punishmentnumber.remove(0);
                    nowpunishmentnumber.remove(0);
                    if(name.size()>0){
                        Intent i=new Intent(SinJikkoActivity.this,SinJikkoActivity.class);
                        //listをActivityに値渡し
                        //Namelist:破ったやつの名前
                        //PunishmentNumberList:罰ゲームを受ける回数
                        //NowPunishmentNumberList:現在そのユーザーが破った回数
                        i.putStringArrayListExtra("NameList",name);
                        i.putStringArrayListExtra("PunishmentNumberList",punishmentnumber);
                        i.putStringArrayListExtra("NowPunishmentNumberList",nowpunishmentnumber);
                        startActivity(i);

                    }else{
                        Intent intent = new Intent(SinJikkoActivity.this, HomeActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });

    }
}
