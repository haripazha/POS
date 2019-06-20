package com.example.acer.jm_pos.reports.summary_sales;

public interface summary_salesContract {

    interface summary_view{
        void populateStartDate(String month,String day,String year);
        void populateEndDate(String month,String day, String year);

        void generateReport();
    }
    interface summary_presenter{
        void showDateDialog();
        void showDateDialogEndDate();

        void storedDateSearchFilter(String start_date,String end_date);
        void storedFilteredStartDate(String month,String day,String year);
        void storedFilteredEndDate(String month,String day, String year);

        void startAndEnd_validationAlert();
    }
}
