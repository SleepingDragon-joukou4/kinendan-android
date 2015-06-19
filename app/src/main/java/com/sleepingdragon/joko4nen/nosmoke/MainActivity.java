package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

/**
 * Created by Ryosei on 2015/06/19.
 */
public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this,
                regist.class);
        startActivity(intent);

    }
}
