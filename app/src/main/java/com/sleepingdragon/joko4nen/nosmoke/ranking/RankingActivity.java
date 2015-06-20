package com.sleepingdragon.joko4nen.nosmoke.ranking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

/**
 * Created by Ryosei on 2015/06/17.
 */
public class RankingActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        Button HomeButton = (Button) findViewById(R.id.HomeButton);
        Button RankingButton = (Button) findViewById(R.id.RankingButton);
        Button ScheduleButton = (Button) findViewById(R.id.ScheduleButton);
        Button SyohinButton = (Button) findViewById(R.id.SyohinButton);


        //HomeButton�������ꂽ�ꍇHome��ʂɑJ��
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        //RankingButton�������ꂽ�ꍇRanking��ʂɑJ��
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        RankingActivity.class);
                startActivity(intent);
            }
        });
        //ScheduleButton�������ꂽ�ꍇschedule��ʂɑJ��
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //SyohinButton�������ꂽ�ꍇsyohin��ʂɑJ��
        SyohinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this,
                        SyohinActivity.class);
                startActivity(intent);
            }
        });


    }

}
