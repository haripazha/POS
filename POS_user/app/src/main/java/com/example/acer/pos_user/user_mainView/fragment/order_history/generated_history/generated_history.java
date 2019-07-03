package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_mainView.fragment.my_order.my_order_adapter.my_order_adapter;
import com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history.generated_history_order_details.generated_history_order_details;

import java.util.ArrayList;

public class generated_history extends AppCompatActivity implements generated_historyContract.generated_historyView{

    //mvp declaration
    generated_historyPresenter presenter;

    //instance
    public static generated_history instance;


    //object declaration
    ImageView back_button;
    TextView item_count;
    TextView date;

    //layout object
    LinearLayout order_history_container;
    LinearLayout no_data_container;
    LinearLayout loading_container;


    //recycler view
    RecyclerView cus_salesHistory_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_history);
        presenter = new generated_historyPresenter(this);
        instance = this;

        //object declaration
        back_button = findViewById(R.id.back_button);
        item_count  = findViewById(R.id.item_count);
        date        = findViewById(R.id.dateFrom);
        order_history_container = findViewById(R.id.order_history_container);
        no_data_container       = findViewById(R.id.no_data_container);
        cus_salesHistory_list   = findViewById(R.id.cus_salesHistory_list);
        loading_container       = findViewById(R.id.loading_container);


        //run this method when system start
        systemStart();

    }

    public void systemStart(){
        //on backpressed
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        //provide loading screen
        no_data_container.setVisibility(View.GONE);
        order_history_container.setVisibility(View.GONE);
        loading_container.setVisibility(View.VISIBLE);

        //add delay time when getting the result
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                //get Order Result
                presenter.get_order_results();
            }
        },1000);

    }

    @Override
    public void populateResult(ArrayList<String> my_order_id, ArrayList<String> my_order_status, ArrayList<String> my_order_customUsername, ArrayList<String> my_order_items, ArrayList<String> my_order_total, ArrayList<String> my_order_day, ArrayList<String> my_order_month, ArrayList<String> my_order_year,
                               String start_day,String start_month,String start_year,
                               String end_day,String end_month,String end_year) {

        if(my_order_id.size()>0){
            item_count.setText("Item Count: "+my_order_id.size()+" items");
            date.setText("Date From "+start_day+"/"+start_month+"/"+start_year+" - "+end_day+"/"+end_month+"/"+end_year);
            //hide loading container
            loading_container.setVisibility(View.GONE);
            order_history_container.setVisibility(View.VISIBLE);
            no_data_container.setVisibility(View.GONE);

            //populate the generated history
            generated_history_adapter adapter = new generated_history_adapter(getApplicationContext());
            adapter.SetData(my_order_id,my_order_status,my_order_customUsername,
                            my_order_items,my_order_total,my_order_day,my_order_month,my_order_year);
            cus_salesHistory_list.setAdapter(adapter);
            cus_salesHistory_list.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        }else{
            //hide loading container
            loading_container.setVisibility(View.GONE);
            order_history_container.setVisibility(View.GONE);
            no_data_container.setVisibility(View.VISIBLE);
        }
    }

    //go to generated history order details class
    @Override
    public void goTo_gen_order_details() {
        Intent intent = new Intent(generated_history.this,generated_history_order_details.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void storeOrder_ID(String order_id){
        presenter.stored_order_id(order_id);
    }

}
