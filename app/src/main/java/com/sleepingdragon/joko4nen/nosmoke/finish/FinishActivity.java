package com.sleepingdragon.joko4nen.nosmoke.finish;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.MainActivity;
import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.regist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FinishActivity extends Activity {
    final String[] TextArray={"禁煙成功！おめでとうございます！",
                                "かなり達成しています！禁煙まであと少し！",
                                "もう少しです。頑張りましょう！",
                                "禁煙までの道のりはまだまだ長そうです・・・",
                                "あなたは禁煙する気がなさそうですね。"};
    TextView UserName;
    TextView Protect;
    TextView Break;
    TextView Solo_Moderation;
    //TextView Team_Moderation;
    TextView Saving;
    TextView Percent;
    TextView FinishText;
    Button NextButton;
    String UserId="";

    SharedPreferences Savedata;
    SharedPreferences.Editor editor;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);
        UserName=(TextView)findViewById(R.id.finish_username);
        Protect=(TextView)findViewById(R.id.finish_protect);
        Break =(TextView)findViewById(R.id.finish_braek);
        Solo_Moderation=(TextView)findViewById(R.id.finish_solo_moderation);
        //Team_Moderation=(TextView)findViewById(R.id.finish_team_moderation);
        Saving=(TextView)findViewById(R.id.finish_saving);
        Percent=(TextView)findViewById(R.id.finish_percent);
        FinishText=(TextView)findViewById(R.id.finish_text);

        NextButton = (Button) findViewById(R.id.finish_firstbutton);
        SharedPreferences SPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        UserId = SPreferences.getString("UserID", "なし");
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().commit();
                startActivity(new Intent(FinishActivity.this,MainActivity.class));
                FinishActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
            @Override
            protected void onPostExecute(JSONArray result) {
                super.onPostExecute(result);
                if(result==null)return;
                try {
                    if(result!=null) {
                            JSONObject ja=result.getJSONObject(0);
                            UserName.setText(ja.getString("UserName"));
                            Protect.setText(ja.getString("AcheiveCount"));
                            Break.setText(ja.getString("DisAcheiveCount"));
                            Solo_Moderation.setText(ja.getString("UserModerationNumber"));
                            //Team_Moderation.setText(ja.getString(""));
                            Saving.setText(ja.getString("ModerationPrice"));

                            int PercentInt=Integer.valueOf(ja.getString("PercentComplete"));
                            Percent.setText(PercentInt+"%");
                            FinishText.setText(TextSetting(PercentInt));


                    }else{
                        Log.d("","Error!");
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
                return;
            }
        };
        //executeで非同期処理開始
        URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=finishselect&TeamId&UserId="
                                    +UserId);
    }
    private String TextSetting(int per) {
        if(per>=100){
            return TextArray[0];
        }else if(per>80) {
            return TextArray[1];
        }else if(per>50){
            return TextArray[2];
        }else if(per>20){
            return TextArray[3];
        }else{
            return TextArray[4];
        }
    }
}
