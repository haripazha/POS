package com.example.acer.pos_kitchen_screen.background_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.acer.pos_kitchen_screen.main.main;

import java.util.Timer;
import java.util.TimerTask;

public class background_services  extends Service {
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

            //get orders
            main m = main.instance;
            if(m!=null){
                m.get_orders();
            }
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



}
