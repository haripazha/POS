package com.example.acer.jm_pos.reports.summary_sales;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.DatePicker;

import com.example.acer.jm_pos.reports.reports_fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class summary_salesPresenter implements summary_salesContract.summary_presenter {
    //Mvp declaration
    summary_salesContract.summary_view mView;
    Context context;


    //object declaration


    //variable declaration
    int month = 0;
    int day = 0;
    int year = 0;


    summary_salesPresenter(summary_salesContract.summary_view view){
        this.mView = view;
        this.context = (Context) mView;


    }


    @Override
    public void showDateDialog() {
        Log.d("date_s","clicled");

        //generate date
        generateDate();

        //show date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mView.populateStartDate(String.valueOf(month+1),String.valueOf(day),String.valueOf(year));
                    }
                }, year, month-1, day);
        datePickerDialog.show();
    }

    @Override
    public void showDateDialogEndDate() {
        Log.d("date_s","clicled");

        //This generate current time
        generateDate();

        //show date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mView.populateEndDate(String.valueOf(month+1),String.valueOf(day),String.valueOf(year));
                    }
                }, year, month-1, day);
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

    @Override
    public void storedFilteredStartDate(String month, String day, String year) {

        //Storing the start_date
        SharedPreferences store_user_id = context.getSharedPreferences("sales_filter_date", Context.MODE_PRIVATE);
        SharedPreferences.Editor store_username_editor = store_user_id.edit();
        store_username_editor.putString("start_day",day);
        store_username_editor.putString("start_month",month);
        store_username_editor.putString("start_year",year);
        store_username_editor.commit();

    }

    @Override
    public void storedFilteredEndDate(String month, String day, String year) {

        //Storing the end_date
        SharedPreferences store_user_id = context.getSharedPreferences("sales_filter_date", Context.MODE_PRIVATE);
        SharedPreferences.Editor store_username_editor = store_user_id.edit();
        store_username_editor.putString("end_day",day);
        store_username_editor.putString("end_month",month);
        store_username_editor.putString("end_year",year);
        store_username_editor.commit();
    }

    @Override
    public void startAndEnd_validationAlert() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Prompt");
        builder1.setMessage("Please select Start and End Date!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    //Generate date
    public void generateDate(){

        //This generate current time
        SimpleDateFormat day_format = new SimpleDateFormat("dd");
        day = Integer.parseInt(day_format.format(new Date()));

        //This generate current time
        SimpleDateFormat month_format = new SimpleDateFormat("M");
        month = Integer.parseInt(month_format.format(new Date()));

        //This generate current time
        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        year = Integer.parseInt(year_format.format(new Date()));

    }
}
