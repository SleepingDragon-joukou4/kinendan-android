package com.sleepingdragon.joko4nen.nosmoke.team_invite;



import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;
import com.sleepingdragon.joko4nen.nosmoke.reg_success.RegSuccessActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    boolean host_frag=false; //Host����
    boolean UserInsert_success=false; //���[�U�[�������Ƒ}���ł������H
    String TeamIDintent=""; //TeamID
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_invite);
        Button invite_next = (Button) findViewById(R.id.invite_next);
        TextView TeamIDTextView = (TextView) findViewById(R.id.team_nameinvite);
        //TeamID��\��
        Intent intent = getIntent();
        if (intent != null) {
            TeamIDintent = intent.getStringExtra("TeamID");
            host_frag=intent.getBooleanExtra("Host",false);
            TeamIDTextView.setText(TeamIDintent);
        }
        //host����Ȃ��Ɖ����Ȃ�
        if(!host_frag) {
            invite_next.setVisibility(View.GONE);
        }


        //���������ꂽ�ꍇreg_success(�o�^�����j��ʂɑJ��
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
        //�܂��AUser��o�^
        UserInsert();
        UserSelect();
    }
    public void UserInsert() {
        //User���擾
        URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
            protected void onPostExecute(JSONArray result) {
                //��������Ajson�`���Ŏ擾�������̂��p�[�X(���)���A�K�؂Ɏ��o���܂�
                //try/catch���Ȃ��Ƒʖڂ��ۂ�
                String Status_insert;
                try {
                    if(result!=null) {
                        JSONObject ja=result.getJSONObject(0);
                        String res=ja.getString("response");
                        if(res.equals("success")) {
                            UserInsert_success=true;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        //execute�Ŕ񓯊������J�n
        URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                "get=userupsert&UserId&TeamId=" + TeamIDintent);
    }
    public void UserSelect() {
            //Timer�Œ���擾
            timer = new Timer();
            timer.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override
                        public void run() {
                            if (UserInsert_success) {
                                URLConnectionAsyncTask URLConnectionTask = new URLConnectionAsyncTask() {
                                    String Invite_TeamID;
                                    String Status;

                                    protected void onPostExecute(JSONArray result) {
                                        //��������Ajson�`���Ŏ擾�������̂��p�[�X(���)���A�K�؂Ɏ��o���܂�
                                        //try/catch���Ȃ��Ƒʖڂ��ۂ�
                                        try {
                                            if (result != null) {
                                                for (int i = 0; i < result.length(); i++) {
                                                    JSONObject ja = result.getJSONObject(i);
                                                    //Invite_TeamName����v����΃`�[�����������Ă���A�����łȂ����NULL
                                                    Invite_TeamID = ja.has("TeamId") ? ja.getString("TeamId") : null;
                                                    if (i == 0) {//Status�i�h�ҋ@��"OR"�o�^����")
                                                        Status = ja.getString("Status");
                                                    }
                                                }
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        if (Invite_TeamID != null && Status.equals("�ҋ@��")) {
                                            //Update
                                        } else if (Invite_TeamID != null && Status.equals("�o�^����")) {
                                            //�o�^����!
                                        }

                                    }
                                };
                                //execute�Ŕ񓯊������J�n
                                URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
                                        "get=teamselect&UserId&TeamId=" + TeamIDintent);
                            }
                        }
                    }, 5000, 5000);//5�b���5�b�Ԋu�Ŏ擾

    }
}
