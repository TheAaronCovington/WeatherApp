package com.example.aaron.weather;

/**
 * Created by Aaron on 7/18/2016.
 */
public class FutureWeather {
    private long mTime;
    private double mTempMin;
    private double mTempMax;
    private String mIcon;

    public int getIconId() {
        int iconId = R.drawable.clear_day_48;

        if (mIcon.equals("clear-day")) {
            iconId = R.drawable.clear_day_48;
        }
        else if (mIcon.equals("clear-night")) {
            iconId = R.drawable.clear_night_48;
        }
        else if (mIcon.equals("rain")) {
            iconId = R.drawable.rain_48;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.drawable.snow_48;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.drawable.sleet_48;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.drawable.wind_48;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.drawable.fog_48;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.drawable.cloudy_48;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy_48;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.drawable.partly_cloudy_48;
        }
        return iconId;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }

    public double getTempMin() {
        return mTempMin;
    }

    public void setTempMin(double mTempMin) {
        this.mTempMin = mTempMin;
    }

    public double getTempMax() {
        return mTempMax;
    }

    public void setTempMax(double mTempMax) {
        this.mTempMax = mTempMax;
    }
}
