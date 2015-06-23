package com.sleepingdragon.joko4nen.nosmoke.home;

import de.greenrobot.event.EventBus;

/**
 * Created by ryu on 15/06/23.
 */
public class TeamDataModel {
    private final EventBus eventBus;

    public TeamDataModel(){
        eventBus = EventBus.getDefault();
    }

    public void getData(){
        String i = "1";
        eventBus.post(new TeamDataEvent(i));
    }
}
