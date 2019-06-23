package com.example.acer.pos_front_desk.main_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.pos_front_desk.R;
import com.example.acer.pos_front_desk.background_services.background_services;

public class main_view extends AppCompatActivity implements  main_viewContract.main_view{

    //mvp declaration
    main_viewPresenter presenter;


    //object declaration
    ImageView connect;
    TextView item_name;
    TextView item_quantity;
    TextView item_price;
    TextView item_total;
    TextView item_subtotal;

    public static main_view instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        presenter = new main_viewPresenter(this);
        instance = this;

        //object declaration
        connect         = findViewById(R.id.connect);
        item_name       = findViewById(R.id.item_name);
        item_quantity   = findViewById(R.id.item_quantity);
        item_price      = findViewById(R.id.item_price);
        item_total      = findViewById(R.id.item_total);
        item_subtotal   = findViewById(R.id.item_subTotal);

        //System start
        systemStart();
    }

    public void systemStart(){

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();
                startService(new Intent(main_view.this,background_services.class));
            }
        });

        //get the data
        presenter.onConnectToMain();
    }

    @Override
    public void populate_the_textView(String item_name_1, String item_quantity_1, String item_price_1, String item_total_1, double item_subTotal_1) {

        //set object data
        item_name.setText(item_name_1);
        item_quantity.setText(item_quantity_1);
        item_price.setText(item_price_1);
        item_total.setText(item_total_1);
        item_subtotal.setText("Total: Php "+String.format("%.2f",item_subTotal_1));
    }
}
