package com.sleepingdragon.joko4nen.nosmoke.MainActivity;


import android.app.Activity;

// 追加
import android.widget.Toast;

import com.sleepingdragon.joko4nen.nosmoke.ConnectionFiles.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.team_create.Team_createActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    // TeamSetting画面に遷移
    URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
        @Override
        protected void onPostExecute(JSONObject result) {

            String Invite_UserName = null;
            try {
                // TeamNameを取得　無かったらnul入れるよ
                Invite_UserName = result.has("TeamId")?result.getString("TeamId"):null;
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
            "get=userupsert&UserId="+  +"&TeamId" + );











/**
    // 追加
    int count = 0;
    private TextView counter_text;
    private Button push_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 追加：クリック
        push_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counter_text.setText(String.valueOf(count));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }**/
}