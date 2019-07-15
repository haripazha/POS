package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_details;

import android.content.Context;

public class booking_detailsPresenter implements booking_detailsContract.booking_presenter {

    //mvp declaration
    booking_detailsContract.booking_view mView;
    Context context;



    booking_detailsPresenter(booking_detailsContract.booking_view view){
        this.mView = view;
        this.context = (Context) mView;

    }
}
