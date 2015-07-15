package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.finish.FinishActivity;
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
    @InjectView(R.id.home_username)
    TextView userName;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
                    .setTitle("アプリケーションの終了")
                    .setMessage("アプリケーションを終了してよろしいですか？")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeActivity.this.finish();
                            HomeActivity.this.moveTaskToBack(true);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .show();
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);
        SharedPreferences sPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userID = sPreferences.getString("UserID", "なし");
        String teamID = sPreferences.getString("TeamID", "なし");
        
        service = new HomeSelectConnectionService(userID, teamID);
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
        String sHonsu = shonsu.getText().toString();
        String mMonsu = mhousu.getText().toString();

        DialogFragment dialogFragment = new HonsuDialogFragment();

        Bundle args = new Bundle();
        args.putStringArray("honsu",new String[]{sHonsu,mMonsu});

        dialogFragment.setArguments(args);
        dialogFragment.show(getFragmentManager(),"dialog");
    }


    public void onSetHonsu(String val){
        shonsu.setText(val);
    }

    /**
     * FinishActivityに遷移するかどうかの判定をし、
     * 合致した場合、FinishActivityに遷移する
     * @param day 残り日数
     * @throws NumberFormatException
     */
    public void finishMatch(String day){
        try {
            if(Integer.parseInt(day) <= 0) {
                //合致した場合
                Intent i=new Intent(this,FinishActivity.class);
                startActivity(i);
                //finishして戻るボタンで戻れなくする
                // 画面移動後アクティビティ消去
                finish();
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return;
    }


    public void onEvent(HomeSelectEvent event) throws JSONException, ParseException {
        if (event.isSuccess()) {
            Log.d(TAG, "HomeSelectEvent async task is success");

            teamName.setText(event.getTeamName());
            userName.setText(event.getUserName());
            teamGenzai.setText(event.getTeamCigaretteNumber());
            teamMokuhyo.setText(event.getSmokinghistoryPerformanceNumberTeamSum());
            mhousu.setText(event.getCigaretteNumber());
            nissu.setText(event.getRemainingDate());
            onSetHonsu(event.getTodayPerformanceNumber());
            finishMatch(event.getRemainingDate());

        } else {
            Log.d(TAG, "HomeSelectEvent async task is failure");
        }
    }


    public void onEvent(SmokingUpsertEvent event) {
        if (event.isSuccess()) {
            Log.d(TAG,event.getMessage());
            service.settingHome();
        } else {
            Log.d(TAG,event.getMessage());
        }
    }
}


