package com.sleepingdragon.joko4nen.nosmoke.util.network;


import java.io.Serializable;

/**
 * Created by ryu on 15/06/25.
 */
abstract class APIConnection implements Serializable {

    public interface ConnectionListener {
        /**
         * JSONの文字列を返す場合
         *
         * @param msg JSONの文字列
         */
        public void onSuccess(String msg);


        /**
         * 失敗
         *
         * @param error 失敗メッセージ
         */
        public void onFailed(String error);
    }

}
