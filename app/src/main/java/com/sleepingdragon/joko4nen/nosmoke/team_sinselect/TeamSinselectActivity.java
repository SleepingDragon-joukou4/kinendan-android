package com.sleepingdragon.joko4nen.nosmoke.team_sinselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;


/**
 * Created by Ryosei on 2015/06/18.
 */
public class TeamSinselectActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_sinselect);
        Button sin_next = (Button) findViewById(R.id.sin_next);
        sin_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Team_invit‰æ–Ê‚É‘JˆÚ
                Intent intent = new Intent(TeamSinselectActivity.this, TeamInviteActivity.class);
                startActivity(intent);
            }
        });

    }
}


