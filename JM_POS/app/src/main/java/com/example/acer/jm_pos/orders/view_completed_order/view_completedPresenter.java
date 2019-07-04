package com.example.acer.jm_pos.orders.view_completed_order;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class view_completedPresenter implements view_completedContract.view_completedPresenter {

    //mvp declaration
    view_completedContract.view_completedView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_orders = localhost + "/MEATSHOP_POS_SALE/android_php/orders/main_orders.php";
    com.example.acer.jm_pos.localhost lc = new localhost();


    view_completedPresenter(view_completedContract.view_completedView view){
        this.mView = view;
        this.context = (Context) mView;


    }

    @Override
    public void getCustomer_orders() {
        //Object declaration
        localhost = lc.getLocalhost();

        //object declaration
        final ArrayList<String> my_order_id = new ArrayList<>();
        final ArrayList<String> my_order_status = new ArrayList<>();
        final ArrayList<String> my_order_customUsername = new ArrayList<>();
        final ArrayList<String> my_order_items = new ArrayList<>();
        final ArrayList<String> my_order_total = new ArrayList<>();

        StringRequest getCustomer_orders = new StringRequest(Request.Method.POST, localhost+main_orders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getCustOrder_res",response);
                if(!response.contains("failed")){
                    if(!response.contains("no_orders")){
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("cust_orders");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                //getString
                                my_order_id.add(jsonObject1.getString("my_order_id"));
                                my_order_status.add(jsonObject1.getString("my_order_status"));
                                my_order_customUsername.add(jsonObject1.getString("my_order_customUsername"));
                                my_order_items.add(jsonObject1.getString("my_order_items"));
                                my_order_total.add(jsonObject1.getString("my_order_total"));

                            }

                            mView.populateMy_orderList(my_order_id,my_order_status,my_order_customUsername,my_order_items,
                                    my_order_total);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(context,"No orders",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"Failed to retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getCustOrder_error",error.toString());
                Toast.makeText(context,"Connection Problem1",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getCompleted_order");



                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getCustomer_orders);
    }
}
