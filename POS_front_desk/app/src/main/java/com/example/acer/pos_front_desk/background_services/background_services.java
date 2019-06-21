package com.example.acer.pos_front_desk.background_services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pos_front_desk.R;
import com.example.acer.pos_front_desk.main_view.main_view;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class background_services extends Service {
    Timer mTimer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        mTimer = new Timer();
        mTimer.schedule(timerTask, 2000, 2*1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);

    }
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.e("LogRunning","Running");

            //get the data
            getData();
        }
    };

    @Override
    public void onDestroy() {
        try{
            mTimer.cancel();
            timerTask.cancel();
        }catch(Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent("com.example.acer.sencia");
        intent.putExtra("yourValue","Torestore");
        sendBroadcast(intent);
    }


    //Get the data from temp_cart
    public void getData(){

        main_view m = main_view.instance;
        if(m!=null){
            m.systemStart();
        }
    }
}
