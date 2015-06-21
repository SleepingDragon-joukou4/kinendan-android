package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

/**
 * Created by Ryosei on 2015/06/19.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Ryosei on 2015/06/19.
 */
public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    SharedPreferences Savedata = null;

    @Override
    protected void onResume() {
        super.onResume();
        //初期表示確認
        //UserIDがあるか確認
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = Savedata.edit();
        String UserData = Savedata.getString("UserID", "なし");
        String TeamData = Savedata.getString("TeamID", "なし");
        Log.d("Userdata", UserData);
        if (TeamData.equals("なし")) {
            //なければ新しく作る
            //User+日付+6文字の乱数(例:User20150620531455)
            String tempUserID = "User";
            //現在日時を取得する
            Calendar c = Calendar.getInstance();
            //フォーマットパターンを指定して表示する
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            tempUserID = tempUserID + sdf.format(c.getTime()).toString();
            Random random = new Random();
            tempUserID = tempUserID + random.nextInt(1000000);
            editor = Savedata.edit();
            editor.putString("UserID", tempUserID);
            editor.apply();
            //registにIntent
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this,regist.class));
                    //finishして戻るボタンで戻れなくする
                    // 画面移動後アクティビティ消去
                    MainActivity.this.finish();
                }
            }, 3000);//3000ms後に画面遷移する
        } else{
            //TeamIDがある場合ホーム画面にintent
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    //finishして戻るボタンで戻れなくする
                    // 画面移動後アクティビティ消去
                    MainActivity.this.finish();
                }
            }, 3000);//3000ms後に画面遷移する
        }
    }
}
