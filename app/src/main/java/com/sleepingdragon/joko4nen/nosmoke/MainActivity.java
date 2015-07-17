package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sleepingdragon.joko4nen.nosmoke.sin_jikko.SinJikkoActivity;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Ryosei on 2015/06/19.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    SharedPreferences Savedata = null;
    String UserID;
    String TeamID;

    //2次元配列
    ArrayList<String> Namelist;
    ArrayList<String> PunishmentNumberlist;
    ArrayList<String> NowPunishmentNumberlist;
    @Override
    protected void onResume() {
        super.onResume();
        //初期表示確認
        //UserIDがあるか確認
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        UserID = Savedata.getString("UserID", "なし");
        TeamID = Savedata.getString("TeamID","なし");
        SharedPreferences.Editor editor = Savedata.edit();
        boolean TeamCreated = Savedata.getBoolean("TeamCreated", false);
        Log.d("TeamCreated",TeamCreated+"");
        if (!TeamCreated) {
            //なければ新しく作る
            //u+日付(月日)+3文字の半角/全角アルファベット(例:u0713dXr)
            String tempUserID = "u";
            //現在日時を取得する
            Calendar c = Calendar.getInstance();
            //フォーマットパターンを指定して表示する
            SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
            tempUserID = tempUserID + sdf.format(c.getTime()).toString();
            tempUserID = tempUserID + RandomStringUtils.randomAlphabetic(3);
            editor = Savedata.edit();
            editor.putString("UserID", tempUserID);
            editor.apply();
            Log.d("UserID",tempUserID);
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
            //変更点ここから
            //Teamの誰かが目標本数を超えていた場合,配列型で名前を取得する
            Namelist=new ArrayList<String>();
            PunishmentNumberlist=new ArrayList<String>();
            NowPunishmentNumberlist=new ArrayList<String>();
            URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
                @Override
                protected void onPostExecute(JSONArray result) {
                    try {

                        Log.d("result", result.toString());

                        if(result!=null && !result.getJSONObject(0).has("response") ) {
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject ja = result.getJSONObject(i);
                                    if (ja.has("Name")) //has:値が存在するときtrue,しないときfalse
                                    {
                                        //罰ゲーム回数を越した時
                                        String Namex=ja.getString("Name");
                                        String PunishmentNumberx=ja.getString("PunishmentNumber");
                                        String NowPunishmentNumberx=ja.getString("NowPunishmentNumber");
                                        if(Integer.parseInt(NowPunishmentNumberx)%Integer.parseInt(PunishmentNumberx)==0) {
                                            Namelist.add(Namex);
                                            PunishmentNumberlist.add(PunishmentNumberx);
                                            NowPunishmentNumberlist.add(NowPunishmentNumberx);
                                        }
                                    }
                                }
                        }
                        //名前(list型）がある場合、sin_jikko画面にintent

                        if(Namelist.size()>0){
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i=new Intent(MainActivity.this,SinJikkoActivity.class);
                                    //listをActivityに値渡し
                                    //Namelist:破ったやつの名前
                                    //PunishmentNumberList:罰ゲームを受ける回数
                                    //NowPunishmentNumberList:現在そのユーザーが破った回数
                                    i.putStringArrayListExtra("NameList",Namelist);
                                    i.putStringArrayListExtra("PunishmentNumberList",PunishmentNumberlist);
                                    i.putStringArrayListExtra("NowPunishmentNumberList",NowPunishmentNumberlist);
                                    startActivity(i);
                                    //finishして戻るボタンで戻れなくする
                                    // 画面移動後アクティビティ消去
                                    MainActivity.this.finish();
                                }
                            }, 3000);//3000ms後に画面遷移する

                        }else{
                            //誰も罰を受けなかった時の処理
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

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            };
            //executeで非同期処理開始
            URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=judgement" +
                    "&UserId="+UserID+"&TeamId="+TeamID);

        }

        }
    }

