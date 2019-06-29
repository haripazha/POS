package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history;

import android.content.Context;

public class generated_historyPresenter implements generated_historyContract.generated_historyPresenter {

    //mvp declaration
    generated_historyContract.generated_historyView mView;
    Context context;


    generated_historyPresenter(generated_historyContract.generated_historyView view){
        this.mView = view;
        this.context = (Context) mView;

    }
}
