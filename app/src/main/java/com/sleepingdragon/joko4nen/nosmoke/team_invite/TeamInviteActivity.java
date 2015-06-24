package com.sleepingdragon.joko4nen.nosmoke.team_invite;



import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.reg_success.RegSuccessActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ryosei on 2015/06/15.
 */
public class TeamInviteActivity extends Activity{
    boolean host_frag=false; //Host判定
    boolean UserInsert_success=false; //ユーザーがちゃんと挿入できたか？
    String TeamIDintent=""; //TeamID
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_invite);
        Button invite_next = (Button) findViewById(R.id.invite_next);
        TextView TeamIDTextView = (TextView) findViewById(R.id.team_nameinvite);
        //TeamIDを表示
        Intent intent = getIntent();
        if (intent != null) {
            TeamIDintent = intent.getStringExtra("TeamID");
            host_frag=intent.getBooleanExtra("Host",false);
            TeamIDTextView.setText(TeamIDintent);
        }
        //hostじゃないと押せない
        if(!host_frag) {
            invite_next.setVisibility(View.GONE);
        }


        //次が押された場合reg_success(登録完了）画面に遷移
        invite_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamInviteActivity.this,
                        RegSuccessActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //まず、Userを登録
        UserInsert();
        UserSelect();
    }
    public void UserInsert() {
        //User情報取得
        SharedPreferences SPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String SUserID = SPreferences.getString("UserID", "なし");
        String SUserName = SPreferences.getString("UserName", "なし");
        String SCigaretteNumber = SPreferences.getString("CigaretteNumber", "なし");
        int SCigaretteBrandNo = SPreferences.getInt("CigaretteBrandNo", 0);
        if(!SUserName.equals("なし") && !SCigaretteNumber.equals("なし") &&
                SCigaretteBrandNo!=0 && !SUserID.equals("なし")) {
            URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
                protected void onPostExecute(JSONArray result) {
                    //ここから、json形式で取得したものをパース(解析)し、適切に取り出します
                    //try/catchしないと駄目っぽい
                    try {
                        if (result != null) {
                            JSONObject ja = result.getJSONObject(0);
                            String res = ja.getString("response");
                            if (res.equals("success")) {
                                Log.d("success", "");
                                UserInsert_success = true;
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
            //executeで非同期処理開始
            URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                    "get=userupsert&UserId="+SUserID+"&Name="+SUserName+"&CigarreteBrandNo="+SCigaretteBrandNo+"&TeamId=" + TeamIDintent
                    +"&CigaretteNumber="+SCigaretteNumber);
        }
    }
    public void UserSelect() {
            //Timerで定期取得
            timer = new Timer();
            timer.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override
                        public void run() {
                            if (UserInsert_success) {
                                URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
                                    ArrayList<String> namelist = new ArrayList<String>();
                                    String Status;

                                    protected void onPostExecute(JSONArray result) {
                                        //ここから、json形式で取得したものをパース(解析)し、適切に取り出します
                                        //try/catchしないと駄目っぽい
                                        try {
                                            if (result != null) {
                                                for (int i = 0; i < result.length(); i++) {
                                                    JSONObject ja = result.getJSONObject(i);
                                                    //Invite_TeamNameが一致すればチーム名を持ってくる、そうでなければNULL
                                                    if(ja.has("TeamName")) {
                                                        namelist.add(ja.getString("TeamName"));
                                                        Log.d("TeamNameList",ja.getString("TeamName"));
                                                    }
                                                    if (i == 0) {//Status（”待機中"OR"登録完了")
                                                        Status = ja.getString("Status");
                                                    }
                                                }
                                                if(namelist!=null && Status.equals("待機中")) {
                                                    //Update
                                                } else if (Status.equals("登録完了")) {
                                                    //登録完了!
                                                }
                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                };
                                //executeで非同期処理開始
                                URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                                        "get=teamselect&UserId&TeamId=" + TeamIDintent);
                            }
                        }
                    }, 5000, 5000);//5秒後に5秒間隔で取得

    }
}
