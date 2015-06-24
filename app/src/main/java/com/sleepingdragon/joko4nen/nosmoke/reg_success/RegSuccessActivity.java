package com.sleepingdragon.joko4nen.nosmoke.reg_success;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.R;



/**
 * Created by Ryosei on 2015/06/18.
 */
public class RegSuccessActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_success);
        Button regsuccess_next =(Button)findViewById(R.id.regsuccess_next);
        regsuccess_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Team_HomeActivity画面に遷移
                Intent intent = new Intent(RegSuccessActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

