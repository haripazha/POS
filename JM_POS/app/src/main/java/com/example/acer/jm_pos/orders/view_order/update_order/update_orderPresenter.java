package com.example.acer.jm_pos.orders.view_order.update_order;

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

import java.util.HashMap;
import java.util.Map;

public class update_orderPresenter implements update_orderContract.update_orderPresenter {
    //mvp declaration
    update_orderContract.update_orderView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_orders = localhost + "/MEATSHOP_POS_SALE/android_php/orders/main_orders.php";
    com.example.acer.jm_pos.localhost lc = new localhost();

    //object declaration
    String my_order_id              = "";
    String my_order_status          = "";
    String my_order_customUsername  = "";
    String my_order_items           = "";
    String my_order_total           = "";
    String my_order_day             = "";
    String my_order_month           = "";
    String my_order_year            = "";
    String customer_name            = "";
    String customer_contact_num     = "";


    update_orderPresenter(update_orderContract.update_orderView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void getOrder_details() {
        //Object declaration
        localhost = lc.getLocalhost();

        //getting the local storage of username
        SharedPreferences sharedPreferences = context.getSharedPreferences("order_details_id", Context.MODE_PRIVATE);
        final String order_details_id = sharedPreferences.getString("order_details_id","");

        final StringRequest get_updateOrders = new StringRequest(Request.Method.POST, localhost + main_orders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_updateOrderRes",response);
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("get_data");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //get json string
                            my_order_id                 = jsonObject1.getString("my_order_id");
                            my_order_status             = jsonObject1.getString("my_order_status");
                            my_order_customUsername     = jsonObject1.getString("my_order_customUsername");
                            my_order_items              = jsonObject1.getString("my_order_items");
                            my_order_total              = jsonObject1.getString("my_order_total");
                            my_order_day                = jsonObject1.getString("my_order_day");
                            my_order_month              = jsonObject1.getString("my_order_month");
                            my_order_year               = jsonObject1.getString("my_order_year");
                            customer_name               = jsonObject1.getString("customer_name");
                            customer_contact_num        = jsonObject1.getString("customer_contact_number");

                        }
                        mView.populate_the_details(my_order_id,
                                                    my_order_status,
                                                    my_order_customUsername,
                                                    my_order_items,
                                                    my_order_total,
                                                    my_order_day,
                                                    my_order_month,
                                                    my_order_year,
                                                    customer_name,
                                                    customer_contact_num);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("get_updateOrdersExcep",e.toString());
                    }

                }else{
                    Toast.makeText(context,"Failed to query!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_updateOrderErr",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_order_details");
                hashMap.put("order_details_id",order_details_id);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_updateOrders);

    }

    @Override
    public void update_orderStatus(final String status, final String order_id) {
        //Object declaration
        localhost = lc.getLocalhost();

        StringRequest updateOrder_status = new StringRequest(Request.Method.POST, localhost + main_orders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("updateOrder_statusRes",response.toString());
                if(!response.contains("failed")){
                    if(response.contains("success")){
                        mView.goTo_order_list();
                    }
                }else{
                    Toast.makeText(context,"Failed to update query!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("updateOrder_statusEr",error.toString());
                Toast.makeText(context,"Connection Problem",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","update_order");
                hashMap.put("update_orderID",order_id);
                hashMap.put("update_orderStatus",status);


                return hashMap;

            }
        };
        Volley.newRequestQueue(context).add(updateOrder_status);
    }
}
