package com.sleepingdragon.joko4nen.nosmoke.util.network;

import android.util.Log;

import com.sleepingdragon.joko4nen.nosmoke.home.SmokingUpsertEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by ryu on 15/07/03.
 */
public class SmokingUpsertConnectionService extends APIConnectionService {
    private final String HOME_SELECT_URL = BASE_URL + "smokingupsert";
    private final String QUERY_USERID = "&UserId=";
    private final String QUERY_TEAMID = "&TeamId=";
    private final String QUERY_PERFORMANCE = "&PerformanceNumber=";
    private final String QUERY_OBJ_NUM = "&ObjectiveNumber=";

    private final EventBus eventBus;
    private String url;
    private JSONObject jsonObject;


    public SmokingUpsertConnectionService() {
        this.eventBus = EventBus.getDefault();
    }

    public SmokingUpsertConnectionService(String userId, String teamId, String performanceNum, String objNum) {
        this.eventBus = EventBus.getDefault();
        url = HOME_SELECT_URL + QUERY_USERID + userId + QUERY_TEAMID + teamId + QUERY_PERFORMANCE + performanceNum + QUERY_OBJ_NUM + objNum;
    }

    public void upsert() {
        Log.d("smokingupsert",url);
        request(url, new ConnectionListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) throws JSONException {
                jsonObject = jsonArray.getJSONObject(0);
                String response = jsonObject.getString("response");
                if (response.equals("success")) {
                    eventBus.post(new SmokingUpsertEvent(true, "UpSertOK"));
                } else if (response.equals("failure")) {
                    eventBus.post(new SmokingUpsertEvent(false, "UpsertFailure"));
                } else {
                    throw new IllegalStateException("値が不正です。");
                }
            }

            @Override
            public void onFailed(String error) {

            }
        });
    }
}
