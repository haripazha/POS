package com.example.acer.jm_pos.orders.view_processing_order;

import java.util.ArrayList;

public interface view_processingContract {
    interface view_processingView{
        void populate_process_order_list(ArrayList<String> my_order_id,
                                         ArrayList<String> my_order_status,
                                         ArrayList<String> my_order_customerUsername,
                                         ArrayList<String> my_order_items,
                                         ArrayList<String> my_order_tota);
    }
    interface view_processingPresenter{
        void get_processing_orders();
    }
}
