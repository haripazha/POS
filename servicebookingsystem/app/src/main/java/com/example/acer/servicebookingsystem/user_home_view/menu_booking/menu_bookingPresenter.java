package com.example.acer.servicebookingsystem.user_home_view.menu_booking;

public class menu_bookingPresenter implements menu_bookingContract.menu_bookingPresenter {

    //mvp declaration
    menu_bookingContract.menu_bookingView mView;

    menu_bookingPresenter(menu_bookingContract.menu_bookingView view){
        this.mView = view;
    }
}
