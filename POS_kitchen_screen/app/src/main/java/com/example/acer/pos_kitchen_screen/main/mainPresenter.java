package com.example.acer.pos_kitchen_screen.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pos_kitchen_screen.localhost;

import java.util.HashMap;
import java.util.Map;

public class mainPresenter implements mainContract.mainPresenter {
    //mvp declaration
    mainContract.mainView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String kitchen_screen = localhost + "/MEATSHOP_POS_SALE/android_php/POS_kitchen_screen/kitchen_screen_main.php";
    localhost lc = new localhost();

    mainPresenter(mainContract.mainView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void get_orders() {
        //Object declaration
        localhost = lc.getLocalhost();

        StringRequest get_orders = new StringRequest(Request.Method.POST, localhost + kitchen_screen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_ordersRes",response.toString());
                if(!response.contains("failed")){
                    if(!response.contains("order_data")){

                    }else{
                        Toast.makeText(context,"No Order",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"Failed to retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_orderError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_orders");


                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_orders);
    }
}
