package com.sleepingdragon.joko4nen.nosmoke.util.network;


import android.util.Log;

import com.sleepingdragon.joko4nen.nosmoke.home.HomeSelectEvent;

import org.json.JSONArray;

import de.greenrobot.event.EventBus;

/**
 * Created by ryu on 15/06/25.
 * Home画面での通信の操作を扱うクラス
 *
 * @author ryu
 * {@link APIConnectionService} 継承元
 */
public class HomeSelectConnectionService extends APIConnectionService {
    private final String HOME_SELECT_URL = BASE_URL + "homeselect";
    private final String QUERY_USERID = "&UserId=";
    private final String QUERY_TEAMID = "&TeamId=";


    private final EventBus eventBus;
    private String url;

    public HomeSelectConnectionService(){
        eventBus = EventBus.getDefault();
    }
    /**
     * コンストラクタ
     *
     * @param useId ユーザーID
     * @param teamId チームID
     */
    public HomeSelectConnectionService(String useId, String teamId){
        eventBus = EventBus.getDefault();
        url = HOME_SELECT_URL + QUERY_USERID + useId + QUERY_TEAMID + teamId;
    }

    /**
     * ホーム画面に取得した情報を設定
     *
     * {@link com.sleepingdragon.joko4nen.nosmoke.util.network.APIConnectionService.ConnectionListener}
     */
    public void settingHome() {
        Log.d("URL", url);
        request(url, new ConnectionListener() {

            @Override
            public void onSuccess(JSONArray jsonArray) {
                eventBus.post(new HomeSelectEvent(true, jsonArray));
            }

            @Override
            public void onFailed(String error) {
                eventBus.post(new HomeSelectEvent(false, null));
                Log.d("settingHome",error);
            }
        });
    }

}