package com.sleepingdragon.joko4nen.nosmoke.teamsetting;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.custom.InputTextCheck;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Ryosei on 2015/06/18.
 */
public class TeamSettingActivity extends Activity {
    EditText teamnametext;
    String teamname;
    EditText batsugametext;
    String batsugame;

    Spinner kikanselectspinner;
    String kikanselectstring;
    Spinner sinnumselectspinner;
    String sinnumselectstring;

    String TeamId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamsetting);

        kikanselectspinner = (Spinner) findViewById(R.id.team_kikanselect);
        kikanselectstring = (String)kikanselectspinner.getSelectedItem();

        sinnumselectspinner = (Spinner) findViewById(R.id.team_sinnumselect);
        sinnumselectstring = (String)kikanselectspinner.getSelectedItem();

        teamnametext = (EditText) findViewById(R.id.team_nametext);
        teamname = teamnametext.getText().toString();

        batsugametext = (EditText)findViewById(R.id.team_batsugametext);
        batsugame = batsugametext.getText().toString();


        ArrayList<String> list = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner kikanselect = (Spinner) findViewById(R.id.team_kikanselect);
        list.add("1");
        list.add("3");
        list.add("6");
        list.add("9");
        list.add("12");
        kikanselect.setAdapter(adapter);

        ArrayList<String> list2 = new ArrayList<String>();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sinnumselect = (Spinner) findViewById(R.id.team_sinnumselect);
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");
        list2.add("9");
        list2.add("10");
        sinnumselect.setAdapter(adapter2);

        Button teamset_next = (Button) findViewById(R.id.teamset_next);
        teamset_next.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                teamname = teamnametext.getText().toString();
                batsugame = batsugametext.getText().toString();
                if(InputTextCheck.inputTextCheck(teamname)&&
                        InputTextCheck.inputTextCheck(batsugame)) {
                    TeamIdCreate();
                    TeamInsert();
                }else{
                    Toast.makeText(TeamSettingActivity.this, "文字をきちんと入力してください！", Toast.LENGTH_LONG).show();
                }


            }

        });
    }
    public  void TeamIdCreate(){
        //t+日付(月日)+3文字の半角/全角アルファベット(例:t0713dXr)
        String tempTeamID = "t";
        //現在日時を取得する
        Calendar c = Calendar.getInstance();
        //フォーマットパターンを指定して表示する
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        tempTeamID = tempTeamID + sdf.format(c.getTime()).toString();
        tempTeamID = tempTeamID + RandomStringUtils.randomAlphabetic(3);
        TeamId = tempTeamID;
        SharedPreferences Teamdata = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = Teamdata.edit();
        editor = Teamdata.edit();
        editor.putString("TeamID", TeamId);
        editor.apply();
    }

    public void TeamInsert(){
        //今日の日付
        Date date1 = new Date();  //(1)Dateオブジェクトを生成
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate=sdf1.format(date1);  //(5)Dateオブジェクトを表示
        // 入力データ取得
        
        kikanselectstring = (String)kikanselectspinner.getSelectedItem();
        sinnumselectstring = (String)kikanselectspinner.getSelectedItem();
        teamname = teamnametext.getText().toString();
        batsugame = batsugametext.getText().toString();
        //UserIDとか取得
        SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        String UserID = Savedata.getString("UserID", "なし");
        String UserName = Savedata.getString("UserName", "なし");
        String CigaretteNumber = Savedata.getString("CigaretteNumber", "なし");
        int CigarreteBrandNo = Savedata.getInt("CigarreteBrandNo", 1);

        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    if(result==null){onError();return;}
                    Log.d("",result.toString());
                    JSONObject ja=result.getJSONObject(0);
                    String res=ja.getString("response");
                    if(res.equals("success")){

                        // team_sinselect画面に遷移(xml)
                        Intent intent = new Intent(TeamSettingActivity.this, TeamInviteActivity.class);
                        //teamIDをActivityに送る
                        intent.putExtra("TeamID",TeamId);
                        Log.d("intent", TeamId);
                        intent.putExtra("Host", true);
                        startActivity(intent);
                        //戻れないようにfinish
                        TeamSettingActivity.this.finish();
                    }else{
                        Log.d("Error","");
                    }


                }catch (JSONException e) {
                    e.printStackTrace();
                }
                return;
            }
        };
        //executeで非同期処理開始
        try {
            URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=teamupsert" +
                    "&UserId="+UserID+"&TeamId="+TeamId+"&CigaretteBrandNo="+CigarreteBrandNo+"&Deadline="+kikanselectstring
                    +"&StartDate="+nowDate+"&Name="+URLEncoder.encode(teamname, "utf-8")+"&Punishment="+ URLEncoder.encode(batsugame, "utf-8")+"&PunishmentNumber="+sinnumselectstring
                    +"&Status=%E5%BE%85%E6%A9%9F%E4%B8%AD"+"&HostUserId="+UserID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}