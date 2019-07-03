package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history.generated_history_order_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.pos_user.R;

public class generated_history_order_details extends AppCompatActivity implements generated_hisContract.gen_hisView{
    //mvp declaration
    generated_hisPresenter presenter;

    //object declaration
    ImageView back_button;
    TextView order_id;
    TextView order_status;
    TextView order_items;
    TextView order_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_history_order_details);
        presenter = new generated_hisPresenter(this);


        //object declaration
        back_button     = findViewById(R.id.back_button);
        order_id        = findViewById(R.id.order_RefID);
        order_status    = findViewById(R.id.order_status);
        order_items     = findViewById(R.id.order_items);
        order_date      = findViewById(R.id.order_date);


        //start this method when system start
        systemStart();
    }

    //run this method when system start
    public void systemStart(){

        //go to back button
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //get the details
        presenter.get_order_details();
    }


    //populate the view details
    @Override
    public void provide_details(String order_id_1, String order_status_1, String order_items_1, String order_total_1, String order_day_1, String order_month_1, String order_year_1) {

        //set variable data
        order_id.setText("Ref ID: "+order_id_1);
        order_status.setText("Status: "+order_status_1);
        order_items.setText(""+order_items_1);
        order_date.setText("Date: "+order_month_1+"/"+order_day_1+"/"+order_year_1);

    }
}
