package com.example.acer.pos_user.user_mainView.fragment.order_history;

public class order_historyPresenter implements order_historyContract.orderHistory_presenter {
    //mvp declaration
    order_historyContract.orderHistory_view mView;



    order_historyPresenter(order_historyContract.orderHistory_view view){
        this.mView = view;

    }
}
