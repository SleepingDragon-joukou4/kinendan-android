package com.sleepingdragon.joko4nen.nosmoke.teamsetting;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.team_sinselect.TeamSinselectActivity;

/**
 * Created by Ryosei on 2015/06/18.
 */
public class TeamSettingActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamsetting);
        Button teamset_next = (Button) findViewById(R.id.teamset_next);
        teamset_next.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // team_sinselect‰æ–Ê‚É‘JˆÚ(xml)
                Intent intent = new Intent(TeamSettingActivity.this, TeamSinselectActivity.class);
                startActivity(intent);
            }

        });
    }
}