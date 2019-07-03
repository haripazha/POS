package com.example.acer.jm_pos.orders.view_completed_order;

import android.content.Context;

public class view_completedPresenter implements view_completedContract.view_completedPresenter {

    //mvp declaration
    view_completedContract.view_completedView mView;
    Context context;


    view_completedPresenter(view_completedContract.view_completedView view){
        this.mView = view;
        this.context = (Context) mView;


    }

}
