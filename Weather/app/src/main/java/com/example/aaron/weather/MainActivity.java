package com.example.aaron.weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG =  MainActivity.class.getSimpleName();
    private CurrentWeather mCurrentWeather;
    private FutureWeather mFutureWeather;
    List<String> dayList = new ArrayList<String>();
    List<String> iconList = new ArrayList<String>();
    List<Integer> highList = new ArrayList<Integer>();
    List<Integer> lowList = new ArrayList<Integer>();

    /*Current Weather TextViews*/
    private TextView lblTemperature;
    private TextView lblHumidity;
    private TextView lblRain;
    private TextView lblSummary;
    private ImageView imgCurrentIcon;

    /*Future Weather TextViews*/
    private TextView lblDay1;
    private TextView lblDay2;
    private TextView lblDay3;
    private TextView lblDay4;
    private TextView lblDay5;
    private TextView lblDay6;
    private ImageView imgDay1;
    private ImageView imgDay2;
    private ImageView imgDay3;
    private ImageView imgDay4;
    private ImageView imgDay5;
    private ImageView imgDay6;
    private TextView lblDay1High;
    private TextView lblDay2High;
    private TextView lblDay3High;
    private TextView lblDay4High;
    private TextView lblDay5High;
    private TextView lblDay6High;
    private TextView lblDay1Low;
    private TextView lblDay2Low;
    private TextView lblDay3Low;
    private TextView lblDay4Low;
    private TextView lblDay5Low;
    private TextView lblDay6Low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();

        double latitude = 36.269539;
        double longitude = -95.854713;
        String apiKey = "aaa9c35c3ee9f5ec0a3059bb80684048";
        String forecastURL = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + ","
                + longitude;
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(jsonData);
                            mFutureWeather = getFutureDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IO Exception caught: ", e);
                    }catch (JSONException e){
                        Log.e(TAG, "JSON Exception caught: ", e);
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "Internet Connection Unavailable", Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "Main UI code is running!");
    }
    private void initializeData(){
        /* Initialize Current Weather Data To Default Values */
        lblHumidity = (TextView)findViewById(R.id.lblHumidityValue);
        lblRain = (TextView)findViewById(R.id.lblRainValue);
        lblSummary = (TextView)findViewById(R.id.lblSummary);
        imgCurrentIcon = (ImageView)findViewById(R.id.imgCurrentIcon);
        lblTemperature = (TextView)findViewById(R.id.lblTemperature);

        /* Initialize Current Weather Data To Default Values */
        lblDay1 = (TextView)findViewById(R.id.lblDay1);
        lblDay2 = (TextView)findViewById(R.id.lblDay2);
        lblDay3 = (TextView)findViewById(R.id.lblDay3);
        lblDay4 = (TextView)findViewById(R.id.lblDay4);
        lblDay5 = (TextView)findViewById(R.id.lblDay5);
        lblDay6 = (TextView)findViewById(R.id.lblDay6);
        lblDay1High = (TextView)findViewById(R.id.lblDay1High);
        lblDay2High = (TextView)findViewById(R.id.lblDay2High);
        lblDay3High = (TextView)findViewById(R.id.lblDay3High);
        lblDay4High = (TextView)findViewById(R.id.lblDay4High);
        lblDay5High = (TextView)findViewById(R.id.lblDay5High);
        lblDay6High = (TextView)findViewById(R.id.lblDay6High);
        lblDay1Low = (TextView)findViewById(R.id.lblDay1Low);
        lblDay2Low = (TextView)findViewById(R.id.lblDay2Low);
        lblDay3Low = (TextView)findViewById(R.id.lblDay3Low);
        lblDay4Low = (TextView)findViewById(R.id.lblDay4Low);
        lblDay5Low = (TextView)findViewById(R.id.lblDay5Low);
        lblDay6Low = (TextView)findViewById(R.id.lblDay6Low);
        imgDay1 = (ImageView)findViewById(R.id.imgDay1);
        imgDay2 = (ImageView)findViewById(R.id.imgDay2);
        imgDay3 = (ImageView)findViewById(R.id.imgDay3);
        imgDay4 = (ImageView)findViewById(R.id.imgDay4);
        imgDay5 = (ImageView)findViewById(R.id.imgDay5);
        imgDay6 = (ImageView)findViewById(R.id.imgDay6);
    }
    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        JSONObject currently = forecast.getJSONObject("currently");
        String timezone = forecast.getString("timezone");
        Log.i(TAG, "From JSON: " + timezone);
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setHumiditiy(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG, currentWeather.getFormattedTime());
        return currentWeather;
    }
    private FutureWeather getFutureDetails(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        JSONObject currently = forecast.getJSONObject("daily");
        JSONArray daily = currently.getJSONArray("data");
        Map<String, String> map = new HashMap<String, String>();
        map.put("Mon", "Monday"); map.put("Tue", "Tuesday"); map.put("Wed", "Wednesday");
        map.put("Thu", "Thursday"); map.put("Fri", "Friday"); map.put("Sat", "Saturday");
        map.put("Sun", "Sunday");

        FutureWeather futureWeather = new FutureWeather();
        for(int i = 1; i < daily.length()-1; i++){
            JSONObject data = daily.getJSONObject(i);
            long time = data.getLong("time");
            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(time * 1000);
            String temp = date.getTime() + "";
            temp = temp.substring(0, 3);
            String day = map.get(temp);
            dayList.add(day);
            iconList.add(data.getString("icon"));
            highList.add(data.getInt("temperatureMax"));
            lowList.add(data.getInt("temperatureMin"));
            Log.i(TAG, day);
        }

        return futureWeather;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError(){
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "err_dialog");
    }
    private void updateDisplay(){
        lblTemperature.setText(mCurrentWeather.getTemperature() + "");
        lblHumidity.setText(mCurrentWeather.getHumiditiy() + "");
        lblRain.setText(mCurrentWeather.getPrecipChance() + "%");
        lblSummary.setText((mCurrentWeather.getSummary()));
        Drawable drawable = getResources().getDrawable(mCurrentWeather.getIconID());
        imgCurrentIcon.setImageDrawable(drawable);

        lblDay1.setText(dayList.get(0));
        lblDay1High.setText(highList.get(0) + "");
        lblDay1Low.setText(lowList.get(0) + "");
        mFutureWeather.setIcon(iconList.get(0));
        drawable = getResources().getDrawable(mFutureWeather.getIconId());
        imgDay1.setImageDrawable(drawable);
        lblDay2.setText(dayList.get(1));
        lblDay2High.setText(highList.get(1) + "");
        lblDay2Low.setText(lowList.get(1) + "");
        mFutureWeather.setIcon(iconList.get(1));
        drawable = getResources().getDrawable(mFutureWeather.getIconId());
        imgDay2.setImageDrawable(drawable);
        lblDay3.setText(dayList.get(2));
        lblDay3High.setText(highList.get(2) + "");
        lblDay3Low.setText(lowList.get(2) + "");
        mFutureWeather.setIcon(iconList.get(2));
        drawable = getResources().getDrawable(mFutureWeather.getIconId());
        imgDay3.setImageDrawable(drawable);
        lblDay4.setText(dayList.get(3));
        lblDay4High.setText(highList.get(3) + "");
        lblDay4Low.setText(lowList.get(3) + "");
        mFutureWeather.setIcon(iconList.get(3));
        drawable = getResources().getDrawable(mFutureWeather.getIconId());
        imgDay4.setImageDrawable(drawable);
        lblDay5.setText(dayList.get(4));
        lblDay5High.setText(highList.get(4) + "");
        lblDay5Low.setText(lowList.get(4) + "");
        mFutureWeather.setIcon(iconList.get(4));
        drawable = getResources().getDrawable(mFutureWeather.getIconId());
        imgDay5.setImageDrawable(drawable);
        lblDay6.setText(dayList.get(5));
        lblDay6High.setText(highList.get(5) + "");
        lblDay6Low.setText(lowList.get(5) + "");
        mFutureWeather.setIcon(iconList.get(5));
        drawable = getResources().getDrawable(mFutureWeather.getIconId());
        imgDay6.setImageDrawable(drawable);

    }

}
