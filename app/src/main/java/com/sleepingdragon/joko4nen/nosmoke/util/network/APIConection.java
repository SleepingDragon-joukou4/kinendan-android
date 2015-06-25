package com.sleepingdragon.joko4nen.nosmoke.util.network;


import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ryu on 15/06/25.
 */
abstract class APIConnection implements Serializable {

    public interface ConnectionListener {
        /**
         * JSONの文字列を返す場合
         *
         * @param msg JSONの文字列
         */
        public void onSuccess(String msg);


        public void onSuccess(JSONArray jsonArray);

        public void onSuccess(JSONObject jsonObject);

        /**
         * 失敗
         *
         * @param error 失敗メッセージ
         */
        public void onFailed(String error);
    }

    public void request(String url, final ConnectionListener listener) {
        URLConnectionAsyncTask urlConnectionAsyncTask = new URLConnectionAsyncTask(){
            @Override
            protected void onPostExecute(JSONArray result) {
                try {
                    listener.onSuccess(result);
                }catch (Exception e){
                    listener.onFailed(e.getMessage());
                }
            }
        };
        urlConnectionAsyncTask.execute(url);
    }
}
//try {
//        if (result != null){
//        listener.onSuccess(result);
//        }
//        }
//        catch (JSONException e){
//        listener.onFailed(e.getMessage());
//        }