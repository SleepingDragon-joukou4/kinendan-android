package com.sleepingdragon.joko4nen.nosmoke.teamsetting;

import com.sleepingdragon.joko4nen.nosmoke.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


/**
 * Created by ryu on 15/06/11.
 */
public class TeamSettingActivity extends Activity {

    EditText teamNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamsetting);

        teamNameText = (EditText)findViewById(R.id.team_nametext);
        

    }
}
