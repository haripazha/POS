package com.example.acer.pos_user.user_mainView.fragment.my_order;

import com.example.acer.pos_user.localhost;

public class my_orderPresenter implements my_orderContract.my_orderPresenter {

    //mvp declaration
    my_orderContract.my_orderView mView;


    //network address Declaration
    String localhost = "";
    String main_MyOrder = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_myOrder/pos_myOrder.php";
    com.example.acer.pos_user.localhost lc = new localhost();

    my_orderPresenter(my_orderContract.my_orderView view){
        this.mView = view;

    }
}
