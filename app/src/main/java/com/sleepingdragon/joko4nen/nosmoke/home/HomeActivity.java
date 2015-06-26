package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;
import com.sleepingdragon.joko4nen.nosmoke.util.network.HomeSelectConnectionService;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * Created by Ryosei on 2015/06/18.
 */
public class HomeActivity extends Activity {

    private final static String TAG = HomeActivity.class.getSimpleName();
    private final EventBus eventBus = EventBus.getDefault();
    private HomeSelectConnectionService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);
        service = new HomeSelectConnectionService("User20150528s4KV2d","Team20150528RazMk4");
        service.settingHome();
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        eventBus.unregister(this);
        super.onPause();
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

    public void onEvent(HomeSelectEvent event){
        if (event.isSuccess()) {
            Log.d(TAG, "My model async task is success");
            Log.d(TAG, event.getJsonObject().toString());
        } else {
            Log.d(TAG, "My model async task is failure");
        }
    }
}


