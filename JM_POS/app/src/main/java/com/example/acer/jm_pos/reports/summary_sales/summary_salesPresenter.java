package com.example.acer.jm_pos.reports.summary_sales;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.DatePicker;

import com.example.acer.jm_pos.reports.reports_fragment;

public class summary_salesPresenter implements summary_salesContract.summary_presenter {
    //Mvp declaration
    summary_salesContract.summary_view mView;
    Context context;


    summary_salesPresenter(summary_salesContract.summary_view view){
        this.mView = view;
        this.context = (Context) mView;


    }


    @Override
    public void showDateDialog() {
        Log.d("date_s","clicled");
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mView.populateStartDate(String.valueOf(month),String.valueOf(day),String.valueOf(year));
                    }
                }, 2019, 06, 19);
        datePickerDialog.show();
    }

    @Override
    public void showDateDialogEndDate() {
        Log.d("date_s","clicled");
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mView.populateEndDate(String.valueOf(month),String.valueOf(day),String.valueOf(year));
                    }
                }, 2019, 06, 19);
        datePickerDialog.show();
    }

    @Override
    public void storedDateSearchFilter(String start_date,String end_date) {

        //Storing the date to localstorage for generating reports
        SharedPreferences store_user_id = context.getSharedPreferences("reports_sales_date", Context.MODE_PRIVATE);
        SharedPreferences.Editor store_username_editor = store_user_id.edit();
        store_username_editor.putString("report_startDate",start_date);
        store_username_editor.putString("report_endDate",end_date);
        store_username_editor.commit();

        //go To generate Report
        mView.generateReport();
    }
}
