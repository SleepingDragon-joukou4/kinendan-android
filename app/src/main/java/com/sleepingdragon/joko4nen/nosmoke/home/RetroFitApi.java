package com.sleepingdragon.joko4nen.nosmoke.home;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ryu on 15/06/23.
 */
public interface RetroFitApi {

    @GET("/api.php")
    void getHomeData(@Query("get") String homeselect,@Query("UserId") String userId, @Query("TeamId") String teamId, Callback<HomeSelect> cd);
}
