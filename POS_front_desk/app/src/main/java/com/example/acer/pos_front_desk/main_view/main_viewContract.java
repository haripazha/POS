package com.example.acer.pos_front_desk.main_view;

public interface main_viewContract {

    interface main_view{
        void populate_the_textView(String item_name,
                                   String item_quantity,
                                   String item_price,
                                   String item_total,
                                   double item_subTotal);
    }
    interface main_presenter{
        void onConnectToMain();
    }
}
