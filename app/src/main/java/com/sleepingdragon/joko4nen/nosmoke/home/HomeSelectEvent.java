package com.sleepingdragon.joko4nen.nosmoke.home;

import org.json.JSONArray;

/**
 * Created by ryu on 15/06/26.
 */
public class HomeSelectEvent {
    private boolean success;
    private JSONArray array;

    public HomeSelectEvent(boolean success, JSONArray array) {
        this.success = success;
        this.array = array;
    }
    public boolean isSuccess() {
        return success;
    }

    public JSONArray getJsonObject() {
        return array;
    }
}
