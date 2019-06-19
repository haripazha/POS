package com.example.acer.jm_pos.reports.summary_sales;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.reports.reports_fragment;
import com.example.acer.jm_pos.reports.summary_sales.generate_reportSales.generate_reportSales;

public class summary_sales extends AppCompatActivity implements summary_salesContract.summary_view{
    //mvp declaration
    summary_salesPresenter presenter;

    //Object declaration
    ImageView back_button;
    EditText start_date;
    EditText end_date;
    Button generate_sales_report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_sales);
        presenter = new summary_salesPresenter(this);

        //object declaration
        back_button = findViewById(R.id.back_button);
        start_date = findViewById(R.id.start_date);
        end_date   = findViewById(R.id.end_date);
        generate_sales_report = findViewById(R.id.generate_sales_report);

        //system start
        systemStart();
    }


    //System start
    public void systemStart(){

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
              finish();
            }
        });

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show dialog when start date clicked
                presenter.showDateDialog();
            }
        });


        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //show dialog when end date click
                presenter.showDateDialogEndDate();
            }
        });

        generate_sales_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.storedDateSearchFilter(start_date.getText().toString(),end_date.getText().toString());
            }
        });
    }

    @Override
    public void populateStartDate(String month, String day, String year) {

        //populate the edittext of date
        start_date.setText(month+"/"+day+"/"+year);


        //stored the date start date details
        presenter.storedFilteredStartDate(month,day,year);

    }

    @Override
    public void populateEndDate(String month, String day, String year) {

        //populate the edittext of date
        end_date.setText(month+"/"+day+"/"+year);

        //stored the date end date details
        presenter.storedFilteredEndDate(month,day,year);
    }

    @Override
    public void generateReport() {

        //Go To generate report
        generate_sales_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(summary_sales.this,generate_reportSales.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });
    }
}
