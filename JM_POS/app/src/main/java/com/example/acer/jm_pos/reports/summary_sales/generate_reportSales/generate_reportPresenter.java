package com.example.acer.jm_pos.reports.summary_sales.generate_reportSales;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.jm_pos.localhost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class generate_reportPresenter implements generate_reportContract.generate_reportPresenter {
    //mvp declaration
    generate_reportContract.generate_reportView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_sales_report = localhost + "/MEATSHOP_POS_SALE/android_php/reports/reports_sales/reports_sales.php";
    com.example.acer.jm_pos.localhost lc = new localhost();

    //variable declaration
    String item_name_string    ="";
    String total_sales_string  ="";
    String total_stocks_string ="";

    double total_sales_value = 0;


    generate_reportPresenter(generate_reportContract.generate_reportView view){
        this.mView = view;
        this.context = (Context) mView;


    }

    //get the start and end date stored locally
    @Override
    public void getstartEndData() {
        //Object declaration
        localhost = lc.getLocalhost();

        //object declaration
        final ArrayList<String> item_id       = new ArrayList<>();
        final ArrayList<String> category_name = new ArrayList<>();
        final ArrayList<String> item_name     = new ArrayList<>();
        final ArrayList<String> item_price    = new ArrayList<>();
        final ArrayList<String> item_desc     = new ArrayList<>();
        final ArrayList<String> item_image    = new ArrayList<>();
        final ArrayList<String> sales_total   = new ArrayList<>();
        final ArrayList<String> item_stock    = new ArrayList<>();



        //get the value for start and end data
        SharedPreferences get_deviceID = context.getSharedPreferences("sales_filter_date", Context.MODE_PRIVATE);
        final String start_day = get_deviceID.getString("start_day","");
        final String start_month = get_deviceID.getString("start_month","");
        final String start_year = get_deviceID.getString("start_year","");

        final String end_day = get_deviceID.getString("end_day","");
        final String end_month = get_deviceID.getString("end_month","");
        final String end_year = get_deviceID.getString("end_year","");

        StringRequest getStartEndData = new StringRequest(Request.Method.POST, localhost + main_sales_report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getStartEndDataRes",response.toString());
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("report_salesData");
                        for(int i = 0; i < jsonArray.length(); i ++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //get json String
                            item_id.add(jsonObject1.getString("item_id"));
                            category_name.add(jsonObject1.getString("category_name"));
                            item_name.add(jsonObject1.getString("item_name"));
                            item_price.add(jsonObject1.getString("item_price"));
                            item_desc.add(jsonObject1.getString("item_desc"));
                            item_image.add(jsonObject1.getString("item_image"));
                            sales_total.add(jsonObject1.getString("sales_total"));
                            item_stock.add(jsonObject1.getString("stocks"));

                            //add to string
                            item_name_string        = item_name_string + jsonObject1.getString("item_name") + "\n";
                            total_sales_string      = total_sales_string + jsonObject1.getString("sales_total") + "\n";
                            total_stocks_string     = total_stocks_string + jsonObject1.getString("stocks") + "\n";

                            //add to total sales value
                            total_sales_value = total_sales_value + Double.parseDouble(jsonObject1.getString("sales_total"));
                        }

                        if(item_name.size()>0){
                            String start_date = start_month +"/"+start_day+"/"+start_year;
                            String end_date   = end_month +"/"+end_day+"/"+end_year;
                            mView.populateChart(item_name,sales_total,item_name_string,total_sales_string,total_stocks_string,start_date,end_date,
                                    total_sales_value);
                        }else {

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(context,"Can't retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getStartEndDateError",error.toString());
                Toast.makeText(context,"Connection Problem",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getReport_Sales");
                hashMap.put("report_startDay",start_day);
                hashMap.put("report_startMonth",start_month);
                hashMap.put("report_startYear",start_year);
                hashMap.put("report_endDay",end_day);
                hashMap.put("report_endMonth",end_month);
                hashMap.put("report_endYear",end_year);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getStartEndData);


    }
}
