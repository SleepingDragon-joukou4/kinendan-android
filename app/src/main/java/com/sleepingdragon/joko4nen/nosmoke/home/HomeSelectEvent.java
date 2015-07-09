package com.sleepingdragon.joko4nen.nosmoke.home;

import android.util.Log;

import com.sleepingdragon.joko4nen.nosmoke.custom.NumberCalc;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * ホーム画面のJsonを扱う
 */
public class HomeSelectEvent {
    private boolean success;
    private JSONObject object;
    String[] acceptFormats = { "yyyy-MM-dd" };
    private Date date;
    private NumberCalc calc;

    public HomeSelectEvent(boolean success, JSONObject result) {
        this.success = success;
        this.object = result;
    }

    /**
     * 結果取得の真偽
     * @return boolean success
     */
    public boolean isSuccess() {
        return success;
    }

    public String getUserId() throws JSONException {
        return object.getString("UserId");
    };

    public String getUserName() throws JSONException {
        return object.getString("Name");
    }

    public String getTeamName() throws JSONException {
        return object.getString("TeamName");
    }

    public Date getTeamStartDate() throws JSONException, ParseException {
        return DateUtils.parseDate(object.getString("TeamStartDate"), acceptFormats);
    }

    public Date getTeamFinalDate() throws JSONException, ParseException {
        return DateUtils.parseDate(object.getString("TeamFinalDate"), acceptFormats);
    }

    public String getDeadline() throws JSONException {
        return object.getString("Deadline");
    }

    public Integer getIntegerDeadline() throws JSONException {
        return object.getInt("Deadline");
    }

    public String getSmokinghistoryPerformanceNumberTeamSum() throws JSONException {
        return object.getString("SmokinghistoryPerformanceNumberTeamSum");
    }

    /**
     * 残り日数を返す。
     *
     * @return String 残り日数
     * @throws JSONException
     * @throws ParseException
     */
    public String getRemainingDate() throws JSONException, ParseException {
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        int remainingDate = diffDays(getTeamFinalDate(), today);

        return Integer.toString(remainingDate);
    }
    /**
     * 当日の個人の目標本数を返す。
     *
     * @return String 当日の個人の目標本数
     * @throws JSONException
     * @throws ParseException
     */
    public String getCigaretteNumber() throws JSONException, ParseException {
        calc = getCalc(object.getInt("CigaretteNumber"));
        return calc.calc().toString();
    }

    /**
     * 当日のチームの目標本数を返す。
     *
     * @return String 当日のチームの目標本数
     * @throws JSONException
     * @throws ParseException
     */
    public String getTeamCigaretteNumber() throws JSONException, ParseException {
        calc = getCalc(object.getInt("TeamCigaretteNumber"));
        return calc.calc().toString();
    }

    public String getTodayPerformanceNumber() throws JSONException, ParseException {
        return object.getString("TodayPerformanceNumber");
    }

    private NumberCalc getCalc(int honsu) throws JSONException, ParseException {
        return new NumberCalc(honsu, getIntegerDeadline().intValue(),NumberOfDaysElapsed());
    }

    private int NumberOfDaysElapsed() throws JSONException, ParseException {
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        Log.d("today",today.toString());
        Log.d("startDay",getTeamStartDate().toString());
        int daysElapsed = diffDays(today,getTeamStartDate());

        Log.d("dayElapsed", Integer.toString(daysElapsed) + "日");
        return daysElapsed;
    }

    private int diffDays(Date dateFrom, Date dateTo){
        // 日付をlong値に変換
        long dateTimeFrom = dateFrom.getTime();
        long dateTimeTo   = dateTo.getTime();

        // 差分の日数を計算
        final int DAY_MILLISECONDS = (1000 * 60 * 60 * 24 );
        long diff = ( dateTimeFrom - dateTimeTo );
        int dayDiff = (int) (diff / DAY_MILLISECONDS);

        return dayDiff;
    }
}
