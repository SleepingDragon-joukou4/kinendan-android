package com.sleepingdragon.joko4nen.nosmoke.util.network;


import android.app.Activity;

import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;

/**
 * Created by ryu on 15/06/25.
 * API接続の継承元クラス
 * これを元にAPI接続時の操作クラスを作成
 *
 * @author ryu
 */
abstract class APIConnectionService implements Serializable {

    protected final String BASE_URL = "http://sleepingdragon.potproject.net/api.php?get=";

    /**
     * API接続後のリスナー
     */
    public interface ConnectionListener {


        /**
         * 成功時の処理
         *
         * @param jsonArray 受け取った結果
         */
        void onSuccess(JSONArray jsonArray) throws JSONException;


        /**
         * 失敗時の処理
         *
         * @param error 失敗メッセージ
         */
        void onFailed(String error);
    }

    /**
     * 指定したURLから
     *
     * @param url 利用するAPI URL
     * @param listener 受け取った結果を操作する。
     */
    protected void request(Activity ac,String url, final ConnectionListener listener){
        URLConnectionAsyncTask urlConnectionAsyncTask = new URLConnectionAsyncTask(ac){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    if(result==null){onError();return;}
                    listener.onSuccess(result);
                }catch (Exception e){
                    listener.onFailed(e.getMessage());
                }
            }
        };
        urlConnectionAsyncTask.execute(url);
    }
}