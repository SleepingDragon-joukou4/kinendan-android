package com.sleepingdragon.joko4nen.nosmoke.reg_success;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.R;

import java.util.ArrayList;


/**
 * Created by Ryosei on 2015/06/18.
 */
public class RegSuccessActivity extends Activity {
    String TeamID;
    String TeamName;
    ArrayList<String> NameLine = new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_success);
        Button regsuccess_next =(Button)findViewById(R.id.regsuccess_next);
        TextView textTeamID=(TextView)findViewById(R.id.reg_nameinvite);
        TextView textTeamName=(TextView)findViewById(R.id.reg_nametextview);
        Intent intent = getIntent();
        if (intent != null) {
            TeamID=intent.getStringExtra("TeamID");
            textTeamID.setText(TeamID);
            TeamName=intent.getStringExtra("TeamName");
            textTeamName.setText(TeamName);
            NameLine=intent.getStringArrayListExtra("NameList");
        }

        //Update
        if(NameLine!=null){
        LinearLayout li =(LinearLayout)findViewById(R.id.reg_bodyline);
        li.removeAllViews();
            for(int i=0;i<NameLine.size();i++) {
                View inviteview = getLayoutInflater().inflate(R.layout.team_inviteview, null);
                li.addView(inviteview);
                TextView textss = (TextView) inviteview.findViewById(R.id.invite_v);
                textss.setText("" + (i + 1));
                TextView textss2 = (TextView) inviteview.findViewById(R.id.invitejoin_v);
                textss2.setText(NameLine.get(i));
            }
        }
        /*SharedPreferences Savedata = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = Savedata.edit();
        editor = Savedata.edit();
        editor.putBoolean("TeamCreated",true);
        editor.apply();*/

        regsuccess_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Team_HomeActivity画面に遷移
                Intent intent = new Intent(RegSuccessActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

