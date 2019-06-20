package com.example.acer.jm_pos.reports.summary_sales.generate_reportSales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.reports.summary_sales.summary_sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class generate_reportSales extends AppCompatActivity implements generate_reportContract.generate_reportView{

    //Presenter declaration
    generate_reportPresenter presenter;

    //Object declaration
    ImageView back_button;
    TextView item_name_view;
    TextView item_sales_view;
    TextView item_stock_view;
    TextView start_date;
    TextView end_date;
    TextView total_sales_value;

    ScrollView with_records;
    LinearLayout no_records;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report_sales);
        presenter = new generate_reportPresenter(this);

        //object declaration
        back_button = findViewById(R.id.back_button);
        item_name_view = findViewById(R.id.item_name);
        item_sales_view = findViewById(R.id.total_sales);
        item_stock_view = findViewById(R.id.total_stock);
        with_records    = findViewById(R.id.report_view);
        no_records      = findViewById(R.id.no_reports);
        start_date      = findViewById(R.id.start_date);
        end_date        = findViewById(R.id.end_date);
        total_sales_value   = findViewById(R.id.total_sales_value);

        //system start
        systemStart();
    }

    public void systemStart(){

        //start generatioon
        presenter.getstartEndData();

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


    @Override
    public void populateChart(ArrayList<String> item_name, ArrayList<String> item_sales,
                              String item_name_string,String total_sales_string,String total_stock_string,
                              String start_date_string,String end_date_string,
                              double total_sales_value_string) {

        with_records.setVisibility(View.VISIBLE);
        no_records.setVisibility(View.GONE);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progressBar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();

        for(int i=0;i<item_name.size();i++){
            Log.d("item_nameList",item_sales.get(i));
            data.add(new ValueDataEntry(item_name.get(i), Double.parseDouble(item_sales.get(i))));
        }

        Column column = cartesian.column(data);
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("Php {%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Product Sales");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("Php {%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Product");
        cartesian.yAxis(0).title("Sales");

        anyChartView.setChart(cartesian);

        //populate the textView
        item_name_view.setText(item_name_string);
        item_sales_view.setText(total_sales_string);
        item_stock_view.setText(total_stock_string);
        start_date.setText("From: "+start_date_string);
        end_date.setText("To: "+end_date_string);
        total_sales_value.setText("Total Sales: Php "+total_sales_value_string);
    }

    @Override
    public void populateChartNoRecords() {
        with_records.setVisibility(View.GONE);
        no_records.setVisibility(View.VISIBLE);
    }
}
