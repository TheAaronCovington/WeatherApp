package com.example.aaron.weather;

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

    public double getTemperature() {
        return mTemperature;
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

    public double getPrecipChance() {
        return mPrecipChance;
    }

    public void setPrecipChance(double mPrecipChance) {
        this.mPrecipChance = mPrecipChance;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }
}
