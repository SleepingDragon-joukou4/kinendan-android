package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;


/**
 * Created by Ryosei on 2015/06/18.
 */
public class HomeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        TextView homeNissu = (TextView) findViewById(R.id.home_nissu);
        TextView homeMhonsu = (TextView) findViewById(R.id.home_mhonsu);
        TextView homeTeamMokuhyo = (TextView) findViewById(R.id.home_mokuhyo);
        TextView homeTeanGenzai = (TextView) findViewById(R.id.home_teamgenzai);

        Button HomeButton = (Button) findViewById(R.id.HomeButton);
        Button RankingButton = (Button) findViewById(R.id.RankingButton);
        Button ScheduleButton = (Button) findViewById(R.id.ScheduleButton);
        Button SyohinButton = (Button) findViewById(R.id.SyohinButton);


        //HomeButton�������ꂽ�ꍇHome��ʂɑJ��
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        //RankingButton�������ꂽ�ꍇRanking��ʂɑJ��
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        RankingActivity.class);
                startActivity(intent);
            }
        });
        //ScheduleButton�������ꂽ�ꍇschedule��ʂɑJ��
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //SyohinButton�������ꂽ�ꍇsyohin��ʂɑJ��
        SyohinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        SyohinActivity.class);
                startActivity(intent);
            }
        });

    }

}


