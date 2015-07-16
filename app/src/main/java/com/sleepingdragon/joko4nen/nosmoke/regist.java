package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.widget.EditText;
import android.widget.Toast;

import com.sleepingdragon.joko4nen.nosmoke.team_create.TeamCreateActivity;
import com.sleepingdragon.joko4nen.nosmoke.custom.InputTextCheck;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class regist extends Activity implements OnClickListener {

    private Button inext;
    String username;
    int sigarettebrand;
    String syokihonsu;
    EditText regnametxt;
    EditText tabakohonsu;
    Spinner tabakosyurui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        TabakoListSelect();
        tabakosyurui = (Spinner) findViewById(R.id.tabakospn);

        sigarettebrand = (int)tabakosyurui.getSelectedItemId();

        regnametxt = (EditText) findViewById(R.id.reg_nametext);
        username = regnametxt.getText().toString();


        tabakohonsu = (EditText)findViewById(R.id.reg_tabakotext);
        syokihonsu = tabakohonsu.getText().toString();


        //�i�ރ{�^��
        inext = (Button) findViewById(R.id.invite_next);
        inext.setOnClickListener(this);



    }



            public void onClick(View v) {

                if (v == inext) {
                    //空白の時の処理
                    username = regnametxt.getText().toString();
                    syokihonsu = tabakohonsu.getText().toString();
                    if(InputTextCheck.inputTextCheck(username)&&
                    InputTextCheck.inputTextCheck(syokihonsu)) {
                        UserDataClientInsert();
                        NextPage();
                    }else{
                        Toast.makeText(this, "文字をきちんと入力してください！", Toast.LENGTH_LONG).show();
                    }

                }
            }

        public void NextPage(){

             Intent goTeamCreate = new Intent(this, TeamCreateActivity.class);
             startActivityForResult(goTeamCreate,0);
            //Intent intent = new Intent(regist.this, Team_createActivity.class);
            //startActivity(intent);
        }
        public void TabakoListSelect(){
            URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(this){
                @Override
                protected void onPostExecute(JSONArray result) {
                    try {

                        Log.d("", result.toString());
                        ArrayList<String> list = new ArrayList<String>();
                        if(result!=null) {
                            for (int i=0;i<result.length();i++){
                                JSONObject ja=result.getJSONObject(i);
                                list.add(ja.getString("CigaretteName"));
                            }
                        }else{
                            list.add("Error!");
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(regist.this, android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner tabakosyurui = (Spinner) findViewById(R.id.tabakospn);
                        tabakosyurui.setAdapter(adapter);

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            };
            //executeで非同期処理開始
            URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=cigarettebrandselect" +
                    "&UserId=0000&TeamId=Team");
        }
        public void UserDataClientInsert(){
            regnametxt = (EditText) findViewById(R.id.reg_nametext);
            username = regnametxt.getText().toString();
            tabakohonsu =(EditText)findViewById(R.id.reg_tabakotext);
            syokihonsu =tabakohonsu.getText().toString();
            tabakosyurui = (Spinner) findViewById(R.id.tabakospn);
            sigarettebrand = (int)tabakosyurui.getSelectedItemId()+1;
            //登録します
            SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = Savedata.edit();
            editor = Savedata.edit();
            editor.putString("UserName", username);
            editor.putString("CigaretteNumber",syokihonsu);
            editor.putInt("CigaretteBrandNo", sigarettebrand);
            editor.apply();

        }







        }







