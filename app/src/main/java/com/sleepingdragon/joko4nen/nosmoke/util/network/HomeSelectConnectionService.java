package com.sleepingdragon.joko4nen.nosmoke.util.network;


import org.json.JSONArray;

/**
 * Created by ryu on 15/06/25.
 */
public class HomeSelectConnectionService extends APIConnectionService {
    private final String HOME_SELECT_URL = BASE_URL + "homeselect";
    private final String USERID = "&UserId=";
    private final String TEAMID = "&TeamId=";

    private String useId = "";
    private String teamId = "";
    private String url = HOME_SELECT_URL + USERID + useId + TEAMID + teamId;

    HomeSelectConnectionService(String useId, String teamId){
        this.useId = useId;
        this.teamId = teamId;
    }

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

