package com.sleepingdragon.joko4nen.nosmoke.sin_jikko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;

/**
 * Created by Ryosei on 2015/06/23.
 */
public class SinJikkoActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sin_jikko);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Button createteam = (Button) findViewById(R.id.regsuccess_next);
        createteam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 「確認」buttonでHpme画面に遷移
                Intent intent = new Intent(SinJikkoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
