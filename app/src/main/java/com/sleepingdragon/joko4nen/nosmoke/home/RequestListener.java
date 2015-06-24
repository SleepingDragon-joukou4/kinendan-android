package com.sleepingdragon.joko4nen.nosmoke.home;

import com.google.gson.Gson;

import retrofit.RetrofitError;

/**
 * Created by ryu on 15/06/24.
 */
public abstract interface RequestListener<T> {
    void onSuccsess(T response);

    void onFailure(RetrofitError error);
}
