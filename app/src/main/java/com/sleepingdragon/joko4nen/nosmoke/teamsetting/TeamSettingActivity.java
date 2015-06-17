package com.sleepingdragon.joko4nen.nosmoke.teamsetting;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.team_create.Team_createActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by ryu on 15/06/11.
 */
public class TeamSettingActivity extends Activity {

     String insertteamname;
     private EditText teamNameText;
     private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamsetting);

        teamNameText = (EditText)findViewById(R.id.team_nametext);
        insertteamname = teamNameText.getText().toString();

        back = (Button)findViewById(R.id.teamset_back );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backpage();

            }
        });


    }
    public void backpage(){

        Intent goTeamCreate = new Intent(this,Team_createActivity.class);
        startActivityForResult(goTeamCreate,0);

    }
}