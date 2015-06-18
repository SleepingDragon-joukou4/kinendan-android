package com.sleepingdragon.joko4nen.nosmoke.team_create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;
import com.sleepingdragon.joko4nen.nosmoke.teamsetting.TeamSettingActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Team_createActivity extends Activity{
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team_create);

            final Button createteam = (Button) findViewById(R.id.createteam);
            createteam.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // TeamSetting画面に遷移
                    Intent intent = new Intent(Team_createActivity.this, TeamSettingActivity.class);
                    startActivity(intent);
            }

        });

            Button createteam_join =(Button)findViewById(R.id.createteam_join);
            createteam_join.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    TextView input = (TextView) findViewById(R.id.createteam_id);
                    String text = input.getText().toString();

                    URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
                        @Override
                        protected void onPostExecute(JSONObject result) {
                            //ここから、json形式で取得したものをパース(解析)し、適切に取り出します
                            //try/catchしないと駄目っぽい
                            String Invite_TeamName = null;
                            try {
                                // TeamNameを取得　無かったらnul入れるよ
                                Invite_TeamName = result.has("TeamId")?result.getString("TeamId"):null;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //ユーザーが入力した情報が
                            if (Invite_TeamName != null) {
                                // 取得した結果をテキストビューに入れるよ
                                //TextView TeamName = (TextView) Team_createActivity.this.findViewById(R.id.createteam_text);
                                //TeamName.setText(Invite_TeamName);
                                //Team_invite画面に遷移
                                //Log.d("onclick", "aa");
                                /**Intent intent = new Intent(Team_createActivity.this,TeamInviteActivity.class);
                                startActivity(intent);
                                **/
                                Toast.makeText(Team_createActivity.this, "チームIDが見つかりました", Toast.LENGTH_LONG).show();
                                //これで、表示されてるはず！
                                //return;
                            }else
                            {
                                Toast.makeText(Team_createActivity.this, "チームIDが見つかりません", Toast.LENGTH_LONG).show();

                            }

                        }
                    };
                    //executeで非同期処理開始
                    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                            "get=teamselect&TeamId="+text);



                }
            });



        }
}