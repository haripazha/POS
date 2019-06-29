package com.example.acer.jm_pos.orders;

public class orderPresenter implements orderContract.contractPresenter {
    //mvp declaration
    orderContract.contractView mView;

    orderPresenter(orderContract.contractView view){
        this.mView = view;
    }
}
