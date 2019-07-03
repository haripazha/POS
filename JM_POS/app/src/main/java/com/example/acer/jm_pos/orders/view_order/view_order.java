package com.example.acer.jm_pos.orders.view_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.orders.view_order.update_order.update_order;
import com.example.acer.jm_pos.reports.top_products.top_products;
import com.example.acer.jm_pos.reports.top_products.top_products_adapter;

import java.util.ArrayList;

public class view_order extends AppCompatActivity implements view_orderContract.viewOrder_view{

    //mvp
    view_orderPresenter presenter;

    //instance
    public static view_order instance;


    //object declaration
    RecyclerView view_order;
    ImageView back_button;

    //instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        presenter = new view_orderPresenter(this);
        instance = this;

        //object declaration
        view_order = findViewById(R.id.view_order);
        back_button = findViewById(R.id.back_button);

        //run this method when activity start;
        systemStart();
    }

    public void systemStart(){
        presenter.getCustomer_orders();


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void populateMy_orderList(ArrayList<String> my_order_id, ArrayList<String> my_order_status, ArrayList<String> my_order_customerUsername, ArrayList<String> my_order_items, ArrayList<String> my_order_total) {

        //Populate item list data
        view_order_adapter adapter = new view_order_adapter(view_order.this);
        adapter.SetData(my_order_id,my_order_status,my_order_customerUsername,
                        my_order_items,my_order_total);
        view_order.setAdapter(adapter);
        view_order.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

    }

    public void go_to_View_order(){
        Intent intent = new Intent(view_order.this,update_order.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
