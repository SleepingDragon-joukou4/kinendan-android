package com.sleepingdragon.joko4nen.nosmoke.home;


import android.app.Application;

import com.google.gson.Gson;

/**
 * Created by ryu on 15/06/23.
 */
public class ApiClientApplication extends Application{
    public static final String API_URL ="http://sleepingdragon.potproject.net";

    interface ApiListener{

        /**
         * Gsonを返す
         *
         */
        public void onSucces(Gson gson);

        public void Failed();

    }


}
