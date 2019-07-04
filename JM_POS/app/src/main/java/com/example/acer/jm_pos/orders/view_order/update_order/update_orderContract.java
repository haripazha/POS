package com.example.acer.jm_pos.orders.view_order.update_order;

public interface update_orderContract {

    interface update_orderView{
        void populate_the_details(String my_order_id,
                                  String my_order_status,
                                  String my_order_customUsername,
                                  String my_order_items,
                                  String my_order_total,
                                  String my_order_day,
                                  String my_order_month,
                                  String my_order_year,
                                  String customer_name,
                                  String customer_contact_num);

        void goTo_order_list();


    }
    interface update_orderPresenter{

        void getOrder_details();
        void update_orderStatus(String status,String order_id);
    }

}
