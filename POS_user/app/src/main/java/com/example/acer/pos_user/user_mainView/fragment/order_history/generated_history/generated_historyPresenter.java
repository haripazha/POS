package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history;

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
import com.example.acer.pos_user.localhost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class generated_historyPresenter implements generated_historyContract.generated_historyPresenter {

    //mvp declaration
    generated_historyContract.generated_historyView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_orderHistory = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_orderHistory/pos_orderHistory.php";
    com.example.acer.pos_user.localhost lc = new localhost();


    generated_historyPresenter(generated_historyContract.generated_historyView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void get_order_results() {
        //Object declaration
        localhost = lc.getLocalhost();

        //get the value for start and end data
        SharedPreferences get_deviceID = context.getSharedPreferences("customer_filter_date", Context.MODE_PRIVATE);
        final String start_day = get_deviceID.getString("start_day","");
        final String start_month = get_deviceID.getString("start_month","");
        final String start_year = get_deviceID.getString("start_year","");

        final String end_day = get_deviceID.getString("end_day","");
        final String end_month = get_deviceID.getString("end_month","");
        final String end_year = get_deviceID.getString("end_year","");

        //object declaration
        final ArrayList<String> my_order_id               = new ArrayList<>();
        final ArrayList<String> my_order_status           = new ArrayList<>();
        final ArrayList<String> my_order_customUsername   = new ArrayList<>();
        final ArrayList<String> my_order_items            = new ArrayList<>();
        final ArrayList<String> my_order_total            = new ArrayList<>();
        final ArrayList<String> my_order_day              = new ArrayList<>();
        final ArrayList<String> my_order_month            = new ArrayList<>();
        final ArrayList<String> my_order_year             = new ArrayList<>();



        //get the value of username
        SharedPreferences get_username = context.getSharedPreferences("customer_username", Context.MODE_PRIVATE);
        final String username = get_username.getString("customer_username","");

        StringRequest get_order_results = new StringRequest(Request.Method.POST, localhost + main_orderHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getOrderRRes",response);
                if(!response.contains("failed")){
                    if(!response.contains("no_data")){

                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("order_data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                //get json string
                                my_order_id.add(jsonObject1.getString("my_order_id"));
                                my_order_status.add(jsonObject1.getString("my_order_status"));
                                my_order_customUsername.add(jsonObject1.getString("my_order_customUsername"));
                                my_order_items.add(jsonObject1.getString("my_order_items"));
                                my_order_total.add(jsonObject1.getString("my_order_total"));
                                my_order_day.add(jsonObject1.getString("my_order_day"));
                                my_order_month.add(jsonObject1.getString("my_order_month"));
                                my_order_year.add(jsonObject1.getString("my_order_year"));
                            }

                            mView.populateResult(my_order_id,my_order_status,my_order_customUsername,
                                                    my_order_items,my_order_total,my_order_day,my_order_month,
                                                    my_order_year,start_day,start_month,start_year,
                                                    end_day,end_month,end_year);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(context,"No Data Found",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"Failed to retrieve query",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getOrderError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_order_history");
                hashMap.put("get_my_orderHistory_username",username);
                hashMap.put("get_startDay",start_day);
                hashMap.put("get_startMonth",start_month);
                hashMap.put("get_startYear",start_year);

                hashMap.put("get_endDay",end_day);
                hashMap.put("get_endMonth",end_month);
                hashMap.put("get_endYear",end_year);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_order_results);


    }

    //stored
    @Override
    public void stored_order_id(String order_id) {

        //Storing the end_date
        SharedPreferences store_user_id = context.getSharedPreferences("history_order_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor store_username_editor = store_user_id.edit();
        store_username_editor.putString("history_order_id",order_id);

        mView.goTo_gen_order_details();
    }
}
