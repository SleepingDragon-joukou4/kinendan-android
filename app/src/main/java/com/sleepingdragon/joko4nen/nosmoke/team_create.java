package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class team_create extends Activity{
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team_create);

            Button btnDisp = (Button)findViewById(R.id.createteam);
            btnDisp.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // Sub 画面を起動
                    Intent intent = new Intent(team_create.this,teamsetting.class);
                    startActivity(intent);
                }
            });
        }
}

