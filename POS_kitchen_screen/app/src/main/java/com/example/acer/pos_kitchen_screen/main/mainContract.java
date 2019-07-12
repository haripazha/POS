package com.example.acer.pos_kitchen_screen.main;

import java.util.ArrayList;

public interface mainContract {

    interface mainView{

        void populateMy_orderList(ArrayList<String> my_order_id,
                                  ArrayList<String> my_order_status,
                                  ArrayList<String> my_order_customerUsername,
                                  ArrayList<String> my_order_items,
                                  ArrayList<String> my_order_tota,
                                  ArrayList<String> my_order_date);

        void clearList();
    }
    interface mainPresenter{
        void get_orders();
        void complete_orders(String order_id);
    }
}
