package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history;

import java.util.ArrayList;

public interface generated_historyContract {
    interface generated_historyView{

        void populateResult(ArrayList<String> my_order_id,
                            ArrayList<String> my_order_status,
                            ArrayList<String> my_order_customUsername,
                            ArrayList<String> my_order_items,
                            ArrayList<String> my_order_total,
                            ArrayList<String> my_order_day,
                            ArrayList<String> my_order_month,
                            ArrayList<String> my_order_year,
                            String start_day,
                            String start_month,
                            String start_year,
                            String end_day,
                            String end_month,
                            String end_year);

        void goTo_gen_order_details();
    }
    interface generated_historyPresenter{

        void get_order_results();
        void stored_order_id(String order_id);

    }
}
