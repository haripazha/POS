package com.example.acer.jm_pos.orders.view_order;

import java.util.ArrayList;

public interface view_orderContract {
    interface viewOrder_view{

        void populateMy_orderList(ArrayList<String> my_order_id,
                                  ArrayList<String> my_order_status,
                                  ArrayList<String> my_order_customerUsername,
                                  ArrayList<String> my_order_items,
                                  ArrayList<String> my_order_tota);

    }
    interface viewOrder_presenter{
        void getCustomer_orders();

    }

}
