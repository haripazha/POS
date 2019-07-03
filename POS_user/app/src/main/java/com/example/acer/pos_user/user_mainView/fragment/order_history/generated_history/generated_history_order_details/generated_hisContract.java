package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history.generated_history_order_details;

public interface generated_hisContract {

    interface gen_hisView{
        void provide_details(String order_id,
                             String order_status,
                             String order_items,
                             String order_total,
                             String order_day,
                             String order_month,
                             String order_year);
    }
    interface gen_hisPresenter{

        void get_order_details();

    }

}
