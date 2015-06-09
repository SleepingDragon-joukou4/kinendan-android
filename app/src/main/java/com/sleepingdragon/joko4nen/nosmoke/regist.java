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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

public class regist extends Activity implements OnClickListener {

    private Button inext;
    private Spinner rspn;
    private String rspnitem[] = {"Spinner", "Spinner 1", "Spinner 2", "Spinner 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        inext = (Button) findViewById(R.id.invite_next);
        inext.setOnClickListener(this);

        ArrayList<String> list = new ArrayList<String>();
        list.add("‚è‚ã‚¤");
        list.add("‚Ö‚Ñ");
        list.add("‚¤‚Ü");
        list.add("‚Ð‚Â‚¶");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.tabakospn);
        spinner.setAdapter(adapter);
    }



            public void onClick(View v) {

                if (v == inext) {
                    Intent intent = new Intent(this, team_create.class);
                    startActivityForResult(intent, 0);


                }
            }
        }


