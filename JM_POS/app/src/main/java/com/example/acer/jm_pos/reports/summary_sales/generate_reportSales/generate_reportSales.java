package com.example.acer.jm_pos.reports.summary_sales.generate_reportSales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.reports.summary_sales.summary_sales;

public class generate_reportSales extends AppCompatActivity {

    //Object declaration
    ImageView back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report_sales);

        //object declaration
        back_button = findViewById(R.id.back_button);


        //system start
        systemStart();


    }

    public void systemStart(){

        //back Button
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generate_reportSales.this,summary_sales.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });
    }
}
