package com.sleepingdragon.joko4nen.nosmoke.team_create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.team_invite.TeamInviteActivity;
import com.sleepingdragon.joko4nen.nosmoke.teamsetting.TeamSettingActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class TeamCreateActivity extends Activity{
        /** Called when the activity is first created. */
        private static final String TAG = "hoge";
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.team_create);

            Button createteam = (Button) findViewById(R.id.createteam);
            createteam.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // TeamSetting画面に遷移
                    Intent intent = new Intent(TeamCreateActivity.this, TeamSettingActivity.class);
                    startActivity(intent);
            }

        });

            Button createteam_join =(Button)findViewById(R.id.createteam_join);
            createteam_join.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    TextView input = (TextView) findViewById(R.id.createteam_id);
                    String text = input.getText().toString();

                    URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
                        String Invite_TeamName;
                        protected void onPostExecute(JSONObject result) {
                            //ここから、json形式で取得したものをパース(解析)し、適切に取り出します
                            //try/catchしないと駄目っぽい
                            try {
                                // TeamNameを取得　無かったらnul入れるよ
                                //JSONObject json = new JSONObject("result");
                                //JSONObject json = result;

                                //JSONObject itemobject = (JSONObject)result.get("Name");
                                //JSONオブジェクト型から、文字列に変換
                                //String san = json.toString(4);
                                //Log.d(TAG, san);

                                //Log.d(TAG,result.getString("TeamId"));
                              Invite_TeamName  = result.has("TeamId")?result.getString("Name"):null;
                                //Toast.makeText(TeamCreateActivity.this, Invite_TeamName, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //ユーザーが入力した情報が
                            if (Invite_TeamName != null) {
                                // 取得した結果をテキストビューに入れるよ
                                //TextView TeamName = (TextView) TeamCreateActivity.this.findViewById(R.id.createteam_text);
                                //TeamName.setText(Invite_TeamName);
                                //Team_invite画面に遷移
                                //Log.d("onclick", "aa");
                                Intent intent = new Intent(TeamCreateActivity.this,TeamInviteActivity.class);
                                //Invite_TeamNameをDATAの名前でTeamInviteActivityへ送信
                                //intent.putExtra("DATA1", Invite_TeamName);
                                startActivity(intent);

                                //Toast.makeText(TeamCreateActivity.this, "チームIDが見つかりました", Toast.LENGTH_LONG).show();
                                //これで、表示されてるはず！
                                //return;
                            }else
                            {
                                Toast.makeText(TeamCreateActivity.this, "チームIDが見つかりません", Toast.LENGTH_LONG).show();

                            }

                        }
                    };
                    //executeで非同期処理開始
                    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                            "get=teamselect2&UserId=&TeamId="+text);



                }
            });



        }
}