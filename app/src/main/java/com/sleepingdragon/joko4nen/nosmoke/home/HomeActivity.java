package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.ranking.RankingActivity;
import com.sleepingdragon.joko4nen.nosmoke.schedule.ScheduleActivity;
import com.sleepingdragon.joko4nen.nosmoke.syohin.SyohinActivity;
import com.sleepingdragon.joko4nen.nosmoke.util.network.HomeSelectConnectionService;

import org.json.JSONException;

import java.text.ParseException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * Created by Ryosei on 2015/06/18.
 */
public class HomeActivity extends Activity {

    private final static String TAG = HomeActivity.class.getSimpleName();
    private final EventBus eventBus = EventBus.getDefault();
    private HomeSelectConnectionService service;

    @InjectView(R.id.home_mhonsu)
    TextView mhousu;
    @InjectView(R.id.home_nissu)
    TextView nissu;
    @InjectView(R.id.teammokuhyo)
    TextView teamMokuhyo;
    @InjectView(R.id.teamgenzai)
    TextView teamGenzai;
    @InjectView(R.id.home_teamname)
    TextView teamName;

    @InjectView(R.id.home_shonsu)
    TextView shonsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);
        service = new HomeSelectConnectionService("User20150528s4KV2d", "Team20150528RazMk4");
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

    @OnClick({R.id.HomeButton, R.id.ScheduleButton,
            R.id.RankingButton, R.id.SyohinButton})
    void onClick(Button button) {
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

    @OnClick(R.id.honsu_button)
    void onClick(View v){
        DialogFragment dialogFragment = new HonsuDialogFragment();
        dialogFragment.show(getFragmentManager(),"dialog");
    }

    public void onSetHonsu(String val){
        shonsu.setText(val);
    }


    public void onEvent(HomeSelectEvent event) throws JSONException, ParseException {
        if (event.isSuccess()) {
            Log.d(TAG, "HomeSelectEvent async task is success");

            teamName.setText(event.getTeamName());
            teamGenzai.setText(event.getSmokinghistoryPerformanceNumberTeamSum());
            teamMokuhyo.setText(event.getTeamCigaretteNumber());
            mhousu.setText(event.getCigaretteNumber());
            nissu.setText(event.getRemainingDate());

        } else {
            Log.d(TAG, "HomeSelectEvent async task is failure");
        }
    }
}


