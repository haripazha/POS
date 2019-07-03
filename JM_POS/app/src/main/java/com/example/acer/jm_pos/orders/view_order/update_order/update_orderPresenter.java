package com.example.acer.jm_pos.orders.view_order.update_order;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.jm_pos.localhost;

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

    update_orderPresenter(update_orderContract.update_orderView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void getOrder_details() {
        //Object declaration
        localhost = lc.getLocalhost();

        final StringRequest get_updateOrders = new StringRequest(Request.Method.POST, localhost + main_orders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_updateOrderRes",response);
                if(!response.contains("failed")){

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
                hashMap.put("function","update_order");


                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_updateOrders);

    }
}
