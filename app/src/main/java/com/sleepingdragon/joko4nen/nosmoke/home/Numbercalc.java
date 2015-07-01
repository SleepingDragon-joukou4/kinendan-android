package com.sleepingdragon.joko4nen.nosmoke.home;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by student on 2015/07/01.
 */
public class Numbercalc {
    int number;
    int day;
    int nowdate;
    public  Numbercalc(int number,int day,int nowdate){
        this.number=number; //初期本数
        this.day=day; //日数
        this.nowdate=nowdate; //今の日付

    }
    public ArrayList<Integer> calc() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for(int i=1;i<=day-3;i++) {
            float percent = 1-(i / day);
            float x = percent * number;
            array.add((int) x);
        }
        for(int i=0;i<array.size();i++) {
            Log.d("array",array.get(i)+"");
        }
        return array;

    }

}
