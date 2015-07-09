package com.sleepingdragon.joko4nen.nosmoke.custom;

/**
 * Created by student on 2015/07/08.
 */
public class InputTextCheck {
    public static boolean inputTextCheck(String text){
        text=text.trim();
        if(text.equals("") || text==null) {
            return false;
        }
        return true;
    }
}
