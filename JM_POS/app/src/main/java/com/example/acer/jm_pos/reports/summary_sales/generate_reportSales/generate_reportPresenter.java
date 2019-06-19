package com.example.acer.jm_pos.reports.summary_sales.generate_reportSales;

import android.content.Context;

public class generate_reportPresenter implements generate_reportContract.generate_reportPresenter {
    //mvp declaration
    generate_reportContract.generate_reportView mView;
    Context context;


    generate_reportPresenter(generate_reportContract.generate_reportView view){
        this.mView = view;
        this.context = (Context) mView;


    }
}
