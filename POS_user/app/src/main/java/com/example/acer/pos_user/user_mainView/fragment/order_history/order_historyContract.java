package com.example.acer.pos_user.user_mainView.fragment.order_history;

import android.content.Context;

public interface order_historyContract {
    interface orderHistory_view{
        void populateStartDate(String month,String day, String year);
        void populateEndDate(String month,String day, String year);
    }
    interface orderHistory_presenter{
        void showStartDateDialog(Context context);
        void showEndDateDialog(Context context);

        void storedFilteredStartDate(String month,String day,String year,Context context);
        void storedFilteredEndDate(String month,String day,String year,Context context);
    }
}
