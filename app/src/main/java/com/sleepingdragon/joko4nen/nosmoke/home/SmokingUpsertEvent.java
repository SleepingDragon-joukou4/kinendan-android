package com.sleepingdragon.joko4nen.nosmoke.home;

/**
 * Created by ryu on 15/07/03.
 */
public class SmokingUpsertEvent {
    private boolean success;
    private String message;

    public SmokingUpsertEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
