package com.example.acer.pos_kitchen_screen.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.pos_kitchen_screen.R;
import com.example.acer.pos_kitchen_screen.background_services.background_services;
import com.example.acer.pos_kitchen_screen.main.adapter.main_adapter;

import java.util.ArrayList;

public class main extends AppCompatActivity implements mainContract.mainView{
    //mvp declaration
    mainPresenter presenter;

    public static main instance;

    //object declaration
    ProgressDialog pd;
    Button connect;
    RecyclerView order_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new mainPresenter(this);
        instance = this;


        pd          = new ProgressDialog(main.this);
        connect     = findViewById(R.id.connect);
        order_list  = findViewById(R.id.order_list);

        //run this method when system start
        systemStart();

    }

    public void systemStart(){

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Connecting...");
                pd.show();
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

    @Override
    public void populateMy_orderList(ArrayList<String> my_order_id, ArrayList<String> my_order_status, ArrayList<String> my_order_customerUsername, ArrayList<String> my_order_items, ArrayList<String> my_order_total,
                                     ArrayList<String> my_order_date) {
        pd.hide();
        Log.d("display_value","Running "+my_order_id.toString());
        //Populate item list data
        main_adapter adapter = new main_adapter(getApplicationContext());
        adapter.SetData(my_order_id,my_order_date,my_order_items);
        order_list.setVisibility(View.VISIBLE);
        order_list.setAdapter(adapter);
        order_list.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
    }

    @Override
    public void clearList() {
       order_list.setVisibility(View.GONE);
    }

    //update orders
    public void complete_orders(String order_id){

        presenter.complete_orders(order_id);

    }
}
