package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main;

import android.content.Context;

import java.util.ArrayList;

public interface order_shop_mainContract {

    interface order_shopView{
        void populateItemList(ArrayList<String> item_id,
                              ArrayList<String> category_name,
                              ArrayList<String> item_name,
                              ArrayList<String> item_price,
                              ArrayList<String> item_desc,
                              ArrayList<String> item_image,
                              ArrayList<String> item_stock);

        void populateCartList(ArrayList<String> order_id,
                              ArrayList<String> order_itemName,
                              ArrayList<String> order_itemQuantity,
                              ArrayList<String> order_customerUsername,
                              ArrayList<String> order_itemPrice,
                              ArrayList<String> order_itemTotal,
                              ArrayList<String> order_itemDate,
                              ArrayList<String> order_itemTime,
                              ArrayList<String> order_status);

        void proceedToDeletion(String custUsername,String itemName);


    }
    interface order_shopPresenter{
        void getItem(String category_name);
        void storeOrders_to_customerOrders(String customerUsername,String item_name,
                                           double item_quantity, double item_price,double item_total,
                                           String item_time,String status);

        void deleteFromCart(String item_name,String cust_name);

        void showDeleteConfirmation(String username,String item_name);

        void submitCustomerOrder();

    }
}
