package com.sleepingdragon.joko4nen.nosmoke;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.EditText;

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
                    Intent intent = new Intent(this, team_create.class);
                    startActivityForResult(intent, 0);


                }
            }





        }


