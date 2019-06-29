package com.example.acer.pos_user.user_mainView.fragment.my_order;

import android.content.Context;

import java.util.ArrayList;

public interface my_orderContract {
    interface my_orderView{
        void populateMyOrderList(ArrayList<String> my_orderId,ArrayList<String> my_order_status,
                                 ArrayList<String> my_order_items,ArrayList<String> my_order_total);
    }
    interface my_orderPresenter{
        void readMyOrders(Context context);

    }
}
