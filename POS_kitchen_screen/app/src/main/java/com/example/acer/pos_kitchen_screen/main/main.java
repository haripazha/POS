package com.example.acer.pos_kitchen_screen.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.pos_kitchen_screen.R;
import com.example.acer.pos_kitchen_screen.background_services.background_services;

public class main extends AppCompatActivity implements mainContract.mainView{
    //mvp declaration
    mainPresenter presenter;

    public static main instance;

    //object declaration
    ProgressDialog pd;
    Button connect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new mainPresenter(this);
        instance = this;


        pd = new ProgressDialog(main.this);
        connect = findViewById(R.id.connect);



        //run this method when system start
        systemStart();

    }

    public void systemStart(){

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startService(new Intent(main.this,background_services.class));
                    }
                },1000);
            }
        });

    }

    //get orders
    public void get_orders(){
        presenter.get_orders();
    }

}
