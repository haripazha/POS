package com.example.acer.pos_user.user_mainView.fragment.order_history;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class order_historyPresenter implements order_historyContract.orderHistory_presenter {
    //mvp declaration
    order_historyContract.orderHistory_view mView;


    //variable declaration
    int month = 0;
    int day = 0;
    int year = 0;


    order_historyPresenter(order_historyContract.orderHistory_view view){
        this.mView = view;

    }

    @Override
    public void showStartDateDialog(Context context) {
        Log.d("date_s","clicled");

        //This generate current time
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
    public void showEndDateDialog(Context context) {
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
    public void storedFilteredStartDate(String month, String day, String year) {

    }

    @Override
    public void storedFilteredEndDate(String month, String day, String year) {

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
