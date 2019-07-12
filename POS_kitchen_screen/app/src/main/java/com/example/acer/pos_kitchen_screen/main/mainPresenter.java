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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

        //object declaration
        final ArrayList<String> my_order_id = new ArrayList<>();
        final ArrayList<String> my_order_status = new ArrayList<>();
        final ArrayList<String> my_order_customUsername = new ArrayList<>();
        final ArrayList<String> my_order_items = new ArrayList<>();
        final ArrayList<String> my_order_total = new ArrayList<>();
        final ArrayList<String> my_order_date = new ArrayList<>();


        StringRequest get_orders = new StringRequest(Request.Method.POST, localhost + kitchen_screen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_ordersRes",response.toString());
                if(!response.contains("failed")){
                    if(!response.contains("no_data")){
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("order_data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                //getString
                                my_order_id.add(jsonObject1.getString("my_order_id"));
                                my_order_status.add(jsonObject1.getString("my_order_status"));
                                my_order_customUsername.add(jsonObject1.getString("my_order_customUsername"));
                                my_order_items.add(jsonObject1.getString("my_order_items"));
                                my_order_total.add(jsonObject1.getString("my_order_total"));
                                my_order_date.add(jsonObject1.getString("my_order_date"));

                            }

                            mView.populateMy_orderList(my_order_id,my_order_status,my_order_customUsername,my_order_items,
                                    my_order_total,my_order_date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("exceptions",e.toString());
                        }
                    }else{
                        mView.clearList();
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

    @Override
    public void complete_orders(final String order_id) {
        //Object declaration
        localhost = lc.getLocalhost();

        StringRequest complete_order = new StringRequest(Request.Method.POST, localhost + kitchen_screen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("complete_orderRes",response);
                if(!response.contains("failed")){
                    if(response.contains("success")){
                        Toast.makeText(context,"Order has been completed!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"Failed to submit query!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("complete_orderErrpr",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","complete_order");
                hashMap.put("com_orderID",order_id);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(complete_order);
    }
}
