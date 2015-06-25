package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Ryosei on 2015/06/18.
 */
public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.HomeButton,R.id.ScheduleButton,
            R.id.RankingButton, R.id.SyohinButton})
    void onClick(Button button){
        switch (button.getId()) {
            case R.id.HomeButton:
                startActivity(new Intent(HomeActivity.this,
                        HomeActivity.class));
                break;
            case R.id.RankingButton:
                startActivity(new Intent(HomeActivity.this,
                        RankingActivity.class));
                break;
            case R.id.ScheduleButton:
                startActivity(new Intent(HomeActivity.this,
                        ScheduleActivity.class));
                break;
            case R.id.SyohinButton:
                startActivity(new Intent(HomeActivity.this,
                        SyohinActivity.class));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}


