package com.sleepingdragon.joko4nen.nosmoke.sin_jikko;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ryosei on 2015/06/23.
 */
public class SinJikkoActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sin_jikko);
    }

    @Override
    protected void onResume() {
        super.onResume();

            Intent i=getIntent();
            String[] name;
            name= i.getStringArrayExtra("NameList");

                    //sin_text1にTextViewにName(目標本数を超えたユーザの名前)を挿入
                    TextView sin_text1 = (TextView) findViewById(R.id.sin_text1);
                    sin_text1.setText(name[0]);


        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        String TeamID = Savedata.getString("TeamID", "なし");
        String UserID = Savedata.getString("UserID", "なし");
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
        @Override
        protected void onPostExecute(JSONArray result) {
            try {
                //罰ゲーム内容を取得
                JSONObject ja = result.getJSONObject(0);
                String Panishment = ja.getString("Panishment");
                //TextView judgement にjudgemen（罰ゲーム内容）を挿入
                TextView judgement = (TextView) findViewById(R.id.judgement);
                judgement.setText(Panishment);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;

        }
    };
    //executeで非同期処理開始
    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=panishmentselect" +
            "&UserId=" + UserID + "&TeamId=" + TeamID);


        // 「確認」buttonでhome画面に遷移
        Button createteam = (Button) findViewById(R.id.regsuccess_next);
        createteam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(SinJikkoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
