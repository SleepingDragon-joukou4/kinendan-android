package com.sleepingdragon.joko4nen.nosmoke.home;

import android.util.Log;

/**
 * Created by ryu on 15/06/23.
 */
public class TeamDataEvent {

    public final String homeNissu;

    public TeamDataEvent(String homeNissu){
        this.homeNissu = homeNissu;
        Log.d("TAG","EventCreate" + homeNissu);
    }

}
