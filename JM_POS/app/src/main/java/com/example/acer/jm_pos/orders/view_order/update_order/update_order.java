package com.example.acer.jm_pos.orders.view_order.update_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.jm_pos.R;

public class update_order extends AppCompatActivity implements update_orderContract.update_orderView{

    public static update_order instance;


    //mvp declaration
    update_orderPresenter presenter;

    //object declaration
    ImageView back_button;
    TextView customer_name;
    TextView customer_contact;
    TextView order_id;
    TextView items;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        presenter = new update_orderPresenter(this);
        instance = this;

        //object declarationn
        back_button         = findViewById(R.id.back_button);
        customer_name       = findViewById(R.id.customer_name);
        customer_contact    = findViewById(R.id.customer_contact);
        order_id            = findViewById(R.id.order_id);
        items               = findViewById(R.id.items);
        total               = findViewById(R.id.total);

        //run this method when system run
        systemStart();

    }

    //run this method when system start
    public void systemStart(){
        //on back pressed
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


    }

    public void getOrder_details(){
        //get the data
        presenter.getOrder_details();
    }

}
