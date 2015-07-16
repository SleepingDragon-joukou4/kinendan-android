package com.sleepingdragon.joko4nen.nosmoke.util.network;


import android.app.Activity;
import android.util.Log;

import com.sleepingdragon.joko4nen.nosmoke.home.HomeSelectEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by ryu on 15/06/25.
 * Home画面での通信の操作を扱うクラス
 *
 * @author ryu
 *         {@link APIConnectionService} 継承元
 */
public class HomeSelectConnectionService extends APIConnectionService {
    private final String HOME_SELECT_URL = BASE_URL + "homeselect";
    private final String QUERY_USERID = "&UserId=";
    private final String QUERY_TEAMID = "&TeamId=";


    private final EventBus eventBus;
    private String url;
    private JSONObject jsonObject;
    public Activity ac;
    public HomeSelectConnectionService() {

        eventBus = EventBus.getDefault();

    }

    /**
     * コンストラクタ
     *
     * @param useId  ユーザーID
     * @param teamId チームID
     * @param ac Activity
     */
    public HomeSelectConnectionService(Activity ac,String useId, String teamId) {
        this.ac=ac;
        eventBus = EventBus.getDefault();
        url = HOME_SELECT_URL + QUERY_USERID + useId + QUERY_TEAMID + teamId;
    }

    /**
     * ホーム画面に取得した情報を設定
     * <p/>
     * {@link com.sleepingdragon.joko4nen.nosmoke.util.network.APIConnectionService.ConnectionListener}
     */
    public void settingHome() {
        Log.d("URL", url);
        request(ac,url, new ConnectionListener() {

            @Override
            public void onSuccess(JSONArray jsonArray) throws JSONException {
                Log.d("Json",jsonArray.toString());
                toJSONObjectByIndex(jsonArray, 0);
                eventBus.post(new HomeSelectEvent(true, jsonObject));
            }

            @Override
            public void onFailed(String error) {
                eventBus.post(new HomeSelectEvent(false, null));
                Log.d("settingHome", error);
            }
        });
    }

    private JSONObject toJSONObjectByIndex(JSONArray jsonArray, int index) throws JSONException {
        jsonObject = jsonArray.getJSONObject(index);
        return jsonObject;
    }

}