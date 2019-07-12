package com.example.acer.servicebookingsystem.background_services;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.acer.servicebookingsystem.user_home_view.home_view;

import java.util.Timer;
import java.util.TimerTask;

public class background_services extends Service {
    Timer mTimer;


    //object declaration
    //location declaration
    LocationManager locationManager;
    String latitude,longitude;

    //Request code declaration
    private static  final int REQUEST_LOCATION=1;



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

            getLocation();
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

    public void getLocation() {



        locationManager=(LocationManager) getSystemService(getApplication().LOCATION_SERVICE);


        //Check Permissions again
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(),

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity) getApplicationContext(),new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Log.d("GPS","Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);

            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Log.d("GPS","Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Log.d("GPS","Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
