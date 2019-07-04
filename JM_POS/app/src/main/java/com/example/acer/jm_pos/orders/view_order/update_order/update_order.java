package com.example.acer.jm_pos.orders.view_order.update_order;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.orders.view_order.view_order;

public class update_order extends AppCompatActivity implements update_orderContract.update_orderView{

    public static update_order instance;


    //mvp declaration
    update_orderPresenter presenter;

    //object declaration
    ImageView back_button;
    TextView customer_names;
    TextView customer_contacts;
    TextView order_ids;
    TextView items;
    TextView totals;
    Button updates;
    String order_id = "";
    Spinner status;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        presenter = new update_orderPresenter(this);
        instance = this;

        //object declarationn
        back_button         = findViewById(R.id.back_button);
        customer_names       = findViewById(R.id.customer_name);
        customer_contacts    = findViewById(R.id.customer_contact);
        order_ids            = findViewById(R.id.order_id);
        items               = findViewById(R.id.items);
        totals               = findViewById(R.id.total);
        updates              = findViewById(R.id.update);
        status               = findViewById(R.id.order_status_update);
        pd                   = new ProgressDialog(update_order.this);

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

        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                pd.setMessage("Submitting...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.update_orderStatus(status.getSelectedItem().toString(),order_id);
                    }
                },1000);

            }
        });

        //get the data
        presenter.getOrder_details();
    }


    @Override
    public void populate_the_details(String my_order_id, String my_order_status, String my_order_customUsername, String my_order_items, String my_order_total, String my_order_day, String my_order_month, String my_order_year,
                                     String customer_name,String customer_contact_num) {

        order_id = my_order_id;

        order_ids.setText("Order ID: "+my_order_id);
        customer_names.setText("Customer Name: "+customer_name);
        customer_contacts.setText("Contacts: "+customer_contact_num);
        items.setText(my_order_items);
        totals.setText("Total: Php "+my_order_total);
    }

    @Override
    public void goTo_order_list() {
        pd.hide();
        Intent intent = new Intent(update_order.this,view_order.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
