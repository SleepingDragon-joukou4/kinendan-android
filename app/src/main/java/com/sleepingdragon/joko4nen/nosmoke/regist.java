package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.view.View.OnClickListener;

public class regist extends Activity implements OnClickListener {

    private Button inext;
    private Spinner nSpinner;
    private String spinnerItems[] = {"Spinner", "Spinner 1", "Spinner 2", "Spinner 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        inext = (Button) findViewById(R.id.invite_next);
        inext.setOnClickListener(this);

    }

            public void onClick(View v) {

                if (v == inext) {
                    Intent intent = new Intent(this, team_create.class);
                    startActivityForResult(intent,0);


            }
        }
    }


