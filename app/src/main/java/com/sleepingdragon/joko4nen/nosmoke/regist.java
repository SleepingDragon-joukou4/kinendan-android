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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import android.widget.EditText;
import java.net.MalformedURLException;

public class regist extends Activity implements OnClickListener {

    private Button inext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);



        //すぴなーの中身セット　本来はDBから取得
        ArrayList<String> list = new ArrayList<String>();
        list.add("りゅう");
        list.add("へび");
        list.add("うま");
        list.add("ひつじ");


        //スピナーのデザイン指定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner tabakosyurui = (Spinner) findViewById(R.id.tabakospn);
        tabakosyurui.setAdapter(adapter);

        //すぴなーで選択されたアイテムを取得
        //int idx = tabakosyurui.getSelectedItemPosition();
        //tabakodbをDBへ登録
        String tabakodb = (String)tabakosyurui.getSelectedItem();


        //テキストボックス指定
        EditText regnametxt = (EditText) findViewById(R.id.reg_nametext);
        //テキストボックスreg_nametextの中身がstrの中へ
        //次へボタンが押されたら、usernameをDBへ登録
        String username = regnametxt.getText().toString();

        //もう一つのテキストボックスの取得
        EditText tabakohonsu = (EditText)findViewById(R.id.reg_tabakotext);
        String syokihonsu = tabakohonsu.getText().toString();


        //進むボタン
        inext = (Button) findViewById(R.id.invite_next);
        inext.setOnClickListener(this);



    }



            public void onClick(View v) {

                if (v == inext) {
                    //次へボタン押したときの処理　たぶんDBへ挿入するSQLもここ

                    Intent intent = new Intent(this, team_create.class);
                    startActivityForResult(intent, 0);

                    connect("http://mixi.jp");



                }
            }




    public static String connect(String strURL) {
        // (1)try-catchによるエラー処理
        try {
            // (2)URLクラスを使用して通信を行う
            URL url = new URL(strURL);
            URLConnection connection = url.openConnection();
            // 動作を入力に設定
            connection.setDoInput(true);
            InputStream stream = connection.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    stream));
            // (3)データの取得
            String data = "";
            String tmp = "";
            while ((tmp = input.readLine()) != null) {
                data += tmp;
            }
            // (4)終了処理
            stream.close();
            input.close();
            return data;
        } catch (Exception e) {
            // (5)エラー処理
            return e.toString();
        }
    }


        }







