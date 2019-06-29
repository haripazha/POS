package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.acer.pos_user.R;

public class order_shop_checkout extends AppCompatActivity implements order_shop_checkoutContract.order_shopCheckView{

    //mvp declaration
    order_shop_checkoutPresenter presenter;

    //object declaration
    ImageView back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_shop_checkout);
        presenter = new order_shop_checkoutPresenter(this);

        //object declaration
        back_button = findViewById(R.id.back_button);




        //run this method when system starts
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

        //get the data customer payment
        //presenter.getUserPayment();
    }
}
