package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class team_create extends Activity implements OnClickListener {

    private Button inext;

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


