package com.example.acer.jm_pos.orders.view_processing_order;

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

public class view_processing_order extends AppCompatActivity implements view_processingContract.view_processingView{
    //mvp declaration
    view_processingPresenter presenter;

    //object declaration
    ImageView back_button;
    RecyclerView processing_order_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_processing_order);
        presenter = new view_processingPresenter(this);

        back_button = findViewById(R.id.back_button);
        processing_order_list = findViewById(R.id.processing_order_list);

        //run this method when system start
        systemStart();

    }

    public void systemStart(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }

    @Override
    public void populate_process_order_list(ArrayList<String> my_order_id, ArrayList<String> my_order_status, ArrayList<String> my_order_customerUsername, ArrayList<String> my_order_items, ArrayList<String> my_order_total) {
        //Populate item list data
        view_order_adapter adapter = new view_order_adapter(view_processing_order.this);
        adapter.SetData(my_order_id,my_order_status,my_order_customerUsername,
                my_order_items,my_order_total);
        processing_order_list.setAdapter(adapter);
        processing_order_list.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
    }
}
