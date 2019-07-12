package com.example.acer.jm_pos.orders.view_completed_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.orders.view_order.view_order;
import com.example.acer.jm_pos.orders.view_order.view_order_adapter;

import java.util.ArrayList;

public class view_completed_order extends AppCompatActivity implements view_completedContract.view_completedView{
    //mvp declaration
    view_completedPresenter presenter;

    //object declaration
    ImageView back_button;
    RecyclerView completed_order_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed_order);
        presenter = new view_completedPresenter(this);

        //object declaration
        back_button = findViewById(R.id.back_button);
        completed_order_list = findViewById(R.id.completed_order_list);


        //run this method when activity start
        systemStart();
    }

    //systemstart Method
    public void systemStart(){

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //get completed orders
        presenter.getCustomer_orders();
    }


    @Override
    public void populateMy_orderList(ArrayList<String> my_order_id, ArrayList<String> my_order_status, ArrayList<String> my_order_customerUsername, ArrayList<String> my_order_items, ArrayList<String> my_order_total) {
        //Populate item list data
        view_order_adapter adapter = new view_order_adapter(view_completed_order.this);
        adapter.SetData(my_order_id,my_order_status,my_order_customerUsername,
                my_order_items,my_order_total);
        completed_order_list.setAdapter(adapter);
        completed_order_list.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
    }
}
