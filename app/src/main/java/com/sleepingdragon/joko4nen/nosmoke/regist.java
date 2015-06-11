package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import android.widget.EditText;

import com.sleepingdragon.joko4nen.nosmoke.team_create.Team_createActivity;

public class regist extends Activity implements OnClickListener {

    private Button inext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);



        //���҂ȁ[�̒��g�Z�b�g�@�{����DB����擾
        ArrayList<String> list = new ArrayList<String>();
        list.add("��イ");
        list.add("�ւ�");
        list.add("����");
        list.add("�Ђ�");


        //�X�s�i�[�̃f�U�C���w��
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner tabakosyurui = (Spinner) findViewById(R.id.tabakospn);
        tabakosyurui.setAdapter(adapter);

        //���҂ȁ[�őI�����ꂽ�A�C�e�����擾
        //int idx = tabakosyurui.getSelectedItemPosition();
        //tabakodb��DB�֓o�^
        String tabakodb = (String)tabakosyurui.getSelectedItem();


        //�e�L�X�g�{�b�N�X�w��
        EditText regnametxt = (EditText) findViewById(R.id.reg_nametext);
        //�e�L�X�g�{�b�N�Xreg_nametext�̒��g��str�̒���
        //���փ{�^���������ꂽ��Ausername��DB�֓o�^
        String username = regnametxt.getText().toString();

        //������̃e�L�X�g�{�b�N�X�̎擾
        EditText tabakohonsu = (EditText)findViewById(R.id.reg_tabakotext);
        String syokihonsu = tabakohonsu.getText().toString();


        //�i�ރ{�^��
        inext = (Button) findViewById(R.id.invite_next);
        inext.setOnClickListener(this);



    }



            public void onClick(View v) {

                if (v == inext) {
                    //���փ{�^���������Ƃ��̏����@���Ԃ�DB�֑}������SQL������

                    Intent intent = new Intent(this, Team_createActivity.class);
                    startActivityForResult(intent, 0);

                    connect("http://mixi.jp");



                }
            }




    public static String connect(String strURL) {
        // (1)try-catch�ɂ��G���[����
        try {
            // (2)URL�N���X���g�p���ĒʐM���s��
            URL url = new URL(strURL);
            URLConnection connection = url.openConnection();
            // �������͂ɐݒ�
            connection.setDoInput(true);
            InputStream stream = connection.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    stream));
            // (3)�f�[�^�̎擾
            String data = "";
            String tmp = "";
            while ((tmp = input.readLine()) != null) {
                data += tmp;
            }
            // (4)�I������
            stream.close();
            input.close();
            return data;
        } catch (Exception e) {
            // (5)�G���[����
            return e.toString();
        }
    }


        }







