package com.sleepingdragon.joko4nen.nosmoke.RegistActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.widget.EditText;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.ConnectionFiles.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.team_create.Team_createActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class regist extends Activity implements OnClickListener {

    private Button inext;
    String username;
    int sigarettebrand;
    String syokihonsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);



        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("s");
        list.add("d");
        list.add("f");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner tabakosyurui = (Spinner) findViewById(R.id.tabakospn);
        tabakosyurui.setAdapter(adapter);

        sigarettebrand = (int)tabakosyurui.getSelectedItemId();


        EditText regnametxt = (EditText) findViewById(R.id.reg_nametext);
        username = regnametxt.getText().toString();


        EditText tabakohonsu = (EditText)findViewById(R.id.reg_tabakotext);
        syokihonsu = tabakohonsu.getText().toString();


        //�i�ރ{�^��
        inext = (Button) findViewById(R.id.invite_next);
        inext.setOnClickListener(this);



    }



            public void onClick(View v) {

                if (v == inext) {

                    URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(){
                        @Override
                        protected void onPostExecute(JSONObject result) {
                            String Punishment;
                            try {
                                Punishment = result.getString(null);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                    };
                    //executeで非同期処理開始
                    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?get=userupsert\n" +
                            "\t\t&UserId=0000&Name=\n" + username + "\t\t&CigarreteBrandNo=\n" + sigarettebrand + "\t\t&\n" +
                            "TeamId=0000&CigaretteNumber=\n" + syokihonsu);

                    nextpage();

                }
            }

        public void nextpage(){

            Intent goTeamCreate = new Intent(this,Team_createActivity.class);
            startActivityForResult(goTeamCreate,0);

        }







        }







