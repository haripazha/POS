package com.example.acer.jm_pos.reports.summary_sales.generate_reportSales;

import java.util.ArrayList;

public interface generate_reportContract {
    interface generate_reportView{

        void populateChart(ArrayList<String> item_name,
                           ArrayList<String> item_sales,
                           String item_name_string,
                           String total_sales_string,
                           String total_stock_string,
                           String start_date,
                           String end_date,
                           double total_sales_value);
        void populateChartNoRecords();

    }
    interface generate_reportPresenter{

        void getstartEndData();
    }
}
