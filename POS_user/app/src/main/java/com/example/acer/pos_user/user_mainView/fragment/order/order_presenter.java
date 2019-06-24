package com.example.acer.pos_user.user_mainView.fragment.order;

public class order_presenter implements order_contract.order_presenter {

    //mvp declaration
    order_contract.order_view mView;


    order_presenter(order_contract.order_view view){
        this.mView = view;

    }

}
