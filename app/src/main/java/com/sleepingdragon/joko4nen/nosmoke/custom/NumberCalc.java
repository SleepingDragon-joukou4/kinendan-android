package com.sleepingdragon.joko4nen.nosmoke.custom;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by student on 2015/07/01.
 */
public class NumberCalc {
    int number;
    int day;
    int nowdate;

    public NumberCalc(int number, int day, int nowdate) {
        this.number = number; //初期本数
        this.day = day; //日数
        this.nowdate = nowdate; //今の日付

    }

    public ArrayList<Integer> calcAll() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 1; i <= day; i++) {
            float percent = 1 - ((float) i / (float) (day - 2));
            float x = (float) percent * (float) number;
            if (x > 0) {
                array.add((int) x + 1);
            } else {
                array.add(0);
            }
        }
        //return array;
        return array;

    }

    public Integer calc() {
        float percent = 1 - ((float) nowdate / (float) (day - 2));
        float x = (float) percent * (float) number;
        if (x > 0) {
            return (int)x;
        } else {
            return 0;
        }
    }
}
