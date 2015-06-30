package com.sleepingdragon.joko4nen.nosmoke.home;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ryu on 15/06/26.
 */
public class HomeSelectEvent {
    private boolean success;
    private JSONObject object;

    public HomeSelectEvent(boolean success, JSONObject result) {
        this.success = success;
        this.object = result;
    }
    public boolean isSuccess() {
        return success;
    }

    public JSONObject getJsonObject() {
        return object;
    }

    public String getUserId() throws JSONException {
        return object.getString("UserId");
    };

    public String getUserName() throws JSONException {
        return object.getString("Name");
    }

    public String getTeamName() throws JSONException {
        return object.getString("TeamName");
    }

    public String getTeamStartDate() throws JSONException {
        return object.getString("TeamStartDate");
    }

    public String getTeamFinalDate() throws JSONException {
        return object.getString("TeamFinalDate");
    }

    public String getDeadline() throws JSONException {
        return object.getString("Deadline");
    }

    public String getSmokinghistoryPerformanceNumberTeamSum() throws JSONException {
        return object.getString("SmokinghistoryPerformanceNumberTeamSum");
    }

    public String getCigaretteNumber() throws JSONException {
        return object.getString("CigaretteNumber");
    }

    public String getTeamCigaretteNumber() throws JSONException {
        return object.getString("TeamCigaretteNumber");
    }
}
