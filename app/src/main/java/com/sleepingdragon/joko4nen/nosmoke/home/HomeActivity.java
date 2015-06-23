package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by Ryosei on 2015/06/18.
 */
public class HomeActivity extends Activity {

    @InjectView(R.id.HomeButton) Button HomeButton;
    @InjectView(R.id.RankingButton) Button RankingButton;
    @InjectView(R.id.ScheduleButton) Button ScheduleButton;
    @InjectView(R.id.SyohinButton) Button SyohinButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);
        //TextView homeMhonsu = (TextView) findViewById(R.id.home_mhonsu);
        //TextView homeTeamMokuhyo = (TextView) findViewById(R.id.home_mokuhyo);
        //TextView homeTeanGenzai = (TextView) findViewById(R.id.home_teamgenzai);
        
        TextView homeNissu = (TextView) findViewById(R.id.home_nissu);
        homeNissu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamDataModel teamDataModel = new TeamDataModel();
                teamDataModel.getData();
            }
        });

        //HomeButton?Ω?Ω?Ω?Ω?Ω?Ω?ΩÍÇΩ?ΩÍç?Home?Ω?Ω Ç…ëJ?Ω?Ω
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        //RankingButton?Ω?Ω?Ω?Ω?Ω?Ω?ΩÍÇΩ?ΩÍç?Ranking?Ω?Ω Ç…ëJ?Ω?Ω
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        RankingActivity.class);
                startActivity(intent);
            }
        });
        //ScheduleButton?Ω?Ω?Ω?Ω?Ω?Ω?ΩÍÇΩ?ΩÍç?schedule?Ω?Ω Ç…ëJ?Ω?Ω
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //SyohinButton?Ω?Ω?Ω?Ω?Ω?Ω?ΩÍÇΩ?ΩÍç?syohin?Ω?Ω Ç…ëJ?Ω?Ω
        SyohinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        SyohinActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(TeamDataEvent event){
        Log.d("TAG",event.homeNissu);
        TextView homeNissu = (TextView) findViewById(R.id.home_nissu);
        homeNissu.setText(event.homeNissu);
    }
}


