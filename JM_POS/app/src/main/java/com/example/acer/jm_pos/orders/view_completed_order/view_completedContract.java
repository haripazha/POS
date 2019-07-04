package com.example.acer.jm_pos.orders.view_completed_order;

import java.util.ArrayList;

public interface view_completedContract {

    interface view_completedView{

        void populateMy_orderList(ArrayList<String> my_order_id,
                                  ArrayList<String> my_order_status,
                                  ArrayList<String> my_order_customerUsername,
                                  ArrayList<String> my_order_items,
                                  ArrayList<String> my_order_tota);
    }
    interface view_completedPresenter{
        void getCustomer_orders();
    }
}
