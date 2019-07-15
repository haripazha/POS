package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.acer.servicebookingsystem.R;

public class booking_details extends AppCompatActivity implements booking_detailsContract.booking_view{
    //mvp declaration
    booking_detailsPresenter presenter;

    //object declaration
    ImageView back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        presenter = new booking_detailsPresenter(this);

        back_button = findViewById(R.id.back_button);

        //systemStart
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

}
