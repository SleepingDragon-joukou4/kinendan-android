package com.sleepingdragon.joko4nen.nosmoke.util.network;


import com.sleepingdragon.joko4nen.nosmoke.URLConnectionAsyncTask;

import org.json.JSONArray;

/**
 * Created by ryu on 15/06/25.
 */
public class HomeConnection extends APIConnection {
    String userId;
    String teamId;

    HomeConnection(String userId, String teamId){
        this.userId = userId;
        this.teamId = userId;

    }

    @Override
    public void request(String url, ConnectionListener listener) {
        URLConnectionAsyncTask urlConnectionAsyncTask = new URLConnectionAsyncTask(){
            @Override
            protected void onPostExecute(JSONArray result) {
                if (result != null) {

                }
            }
        };
    }
}

