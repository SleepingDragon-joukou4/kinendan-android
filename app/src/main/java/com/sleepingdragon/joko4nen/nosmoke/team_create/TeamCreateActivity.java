package com.sleepingdragon.joko4nen.nosmoke.team_create;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.custom.InputTextCheck;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;
import com.sleepingdragon.joko4nen.nosmoke.teamsetting.TeamSettingActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TeamCreateActivity extends Activity{
        /** Called when the activity is first created. */
        private static final String TAG = "hoge";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team_create);
        }
        @Override
        protected void onResume() {
            super.onResume();
            Button createteam = (Button) findViewById(R.id.createteam);
            createteam.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // 「チームを作成する」buttonでTeamSetting画面に遷移
                    Intent intent = new Intent(TeamCreateActivity.this, TeamSettingActivity.class);
                    startActivity(intent);
            }
        });
            Button createteam_join =(Button)findViewById(R.id.createteam_join);
            createteam_join.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    TextView input = (TextView) findViewById(R.id.createteam_id);
                    String text = input.getText().toString();
                    if(!InputTextCheck.inputTextCheck(text)) {
                        Toast.makeText(TeamCreateActivity.this, "文字をきちんと入力してください！", Toast.LENGTH_LONG).show();
                        return;
                    }

                    URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask(TeamCreateActivity.this) {
                        String Invite_TeamID;
                        String Status;
                        protected void onPostExecute(JSONArray result) {
                            //ここから、json形式で取得したものをパース(解析)し、適切に取り出します
                            //try/catchしないと駄目っぽい
                            try {
                                if(result==null){onError();return;}
                                    for (int i=0;i<result.length();i++){
                                        JSONObject ja=result.getJSONObject(i);
                                        //Invite_TeamNameが一致すればチーム名を持ってくる、そうでなければNULL
                                        Invite_TeamID = ja.has("TeamId") ? ja.getString("TeamId") : null;
                                        if(i==0) {//Status（”待機中"OR"登録完了")
                                            Status = ja.getString("Status");
                                        }
                                    }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            /**
                            ユーザーが入力した情報(チームID)がある、かつチームステータスが待機中である場合
                            TeamInviteActivity飛ぶ
                             **/
                            if (Invite_TeamID != null && Status.equals("待機中")) {
                                Intent intent = new Intent(TeamCreateActivity.this, TeamInviteActivity.class);
                                //Invite_TeamIDをDATAの名前でTeamInviteActivityへ送信
                                intent.putExtra("TeamID",Invite_TeamID);
                                intent.putExtra("Host",false);
                                startActivity(intent);
                                //finish
                                TeamCreateActivity.this.finish();
                            /**
                             チームIDが見つからないもしくはチームステータスが登録完了の場合
                             警告を促す
                             **/
                            } else {
                                Toast.makeText(TeamCreateActivity.this, "チームが見つかりません", Toast.LENGTH_LONG).show();

                            }

                        }
                    };
                    //executeで非同期処理開始
                    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                            "get=teamselect&UserId&TeamId=" + text);
                }

            });
        }
}