package com.example.aaron.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Aaron on 7/13/2016.
 */
public class CurrentWeather {
    private String mSummary;
    private String mIcon;
    private double mTemperature;
    private double mHumiditiy;
    private double mPrecipChance;
    private long mTime;
    private String mTimeZone;



    /*Getter and Setter Methods */
    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public int getIconID(){
        int iconId = R.drawable.clear_day;

        if (mIcon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (mIcon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (mIcon.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public void setTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public double getHumiditiy() {
        return mHumiditiy;
    }

    public void setHumiditiy(double mHumiditiy) {
        this.mHumiditiy = mHumiditiy;
    }

    public int getPrecipChance() {
        return (int)Math.round(mPrecipChance*100);
    }

    public void setPrecipChance(double mPrecipChance) {
        this.mPrecipChance = mPrecipChance;
    }

    public long getTime() {
        return mTime;
    }

    public String getFormattedTime(){
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        format.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime() * 1000);
        String timeString = format.format(dateTime);
        return timeString;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String mTimeZone) {
        this.mTimeZone = mTimeZone;
    }
}
