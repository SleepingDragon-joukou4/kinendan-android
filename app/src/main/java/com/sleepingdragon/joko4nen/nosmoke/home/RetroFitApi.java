package com.sleepingdragon.joko4nen.nosmoke.home;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ryu on 15/06/23.
 */
public interface RetroFitApi {

    @GET("userselect&UserID={userid}")
    void getUser(@Path("userid") String userId);
}
