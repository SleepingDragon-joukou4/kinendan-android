package com.sleepingdragon.joko4nen.nosmoke.team_create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;
import com.sleepingdragon.joko4nen.nosmoke.teamsetting.TeamSettingActivity;

public class Team_createActivity extends Activity{
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team_create);

            Button createteam = (Button)findViewById(R.id.createteam);
            createteam.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // TeamSetting画面に遷移
                    Intent intent = new Intent(Team_createActivity.this,TeamSettingActivity.class);
                    startActivity(intent);
                }







            });


            Button createteam_join =(Button)findViewById(R.id.createteam_join);
            createteam_join.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    //Team_invite画面に遷移
                    Log.d("onclick", "aa");
                    Intent intent = new Intent(Team_createActivity.this,TeamInviteActivity.class);
                    startActivity(intent);



                }
            });



        }
}

