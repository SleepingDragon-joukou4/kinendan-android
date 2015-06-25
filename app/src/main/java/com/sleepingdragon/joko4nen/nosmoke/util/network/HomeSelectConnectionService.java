package com.sleepingdragon.joko4nen.nosmoke.util.network;


import org.json.JSONArray;

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

    private String useId = "";
    private String teamId = "";
    private String url = HOME_SELECT_URL + QUERY_USERID + useId + QUERY_TEAMID + teamId;

    /**
     * コンストラクタ
     *
     * @param useId ユーザーID
     * @param teamId チームID
     */
    HomeSelectConnectionService(String useId, String teamId){
        this.useId = useId;
        this.teamId = teamId;
    }

    /**
     * ホーム画面に取得した情報を設定
     *
     * {@link com.sleepingdragon.joko4nen.nosmoke.util.network.APIConnectionService.ConnectionListener}
     */
    public void settingHome(){
        request(url,new ConnectionListener(){

            @Override
            public void onSuccess(JSONArray jsonArray) {
                System.out.println(jsonArray);
            }

            @Override
            public void onFailed(String error) {
                System.out.println(error);
                //Log.d("settingHome",error);
            }
        });
    }
}

