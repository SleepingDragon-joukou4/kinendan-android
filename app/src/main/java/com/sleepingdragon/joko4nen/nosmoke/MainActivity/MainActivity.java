package com.sleepingdragon.joko4nen.nosmoke.MainActivity;


import android.app.Activity;

// �ǉ�
import android.widget.Toast;

import com.sleepingdragon.joko4nen.nosmoke.ConnectionFiles.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.team_create.Team_createActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    // TeamSetting��ʂɑJ��
    URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
        @Override
        protected void onPostExecute(JSONObject result) {

            String Invite_UserName = null;
            try {
                // TeamName���擾�@����������nul������
                Invite_UserName = result.has("TeamId")?result.getString("TeamId"):null;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //���[�U�[�����͂������
            if (Invite_TeamName != null) {
                // �擾�������ʂ��e�L�X�g�r���[�ɓ�����
                //TextView TeamName = (TextView) Team_createActivity.this.findViewById(R.id.createteam_text);
                //TeamName.setText(Invite_TeamName);
                //Team_invite��ʂɑJ��
                //Log.d("onclick", "aa");
                /**Intent intent = new Intent(Team_createActivity.this,TeamInviteActivity.class);
                 startActivity(intent);
                 **/
                Toast.makeText(Team_createActivity.this, "�`�[��ID��������܂���", Toast.LENGTH_LONG).show();
                //����ŁA�\������Ă�͂��I
                //return;
            }else
            {
                Toast.makeText(Team_createActivity.this, "�`�[��ID��������܂���", Toast.LENGTH_LONG).show();

            }

        }
    };
    //execute�Ŕ񓯊������J�n
    URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
            "get=userupsert&UserId="+  +"&TeamId" + );











/**
    // �ǉ�
    int count = 0;
    private TextView counter_text;
    private Button push_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �ǉ��F�N���b�N
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