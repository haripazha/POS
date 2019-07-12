package com.example.acer.jm_pos.orders.view_processing_order;

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

public class view_processingPresenter implements view_processingContract.view_processingPresenter {
    //mvp declaration
    view_processingContract.view_processingView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_orders = localhost + "/MEATSHOP_POS_SALE/android_php/orders/main_orders.php";
    com.example.acer.jm_pos.localhost lc = new localhost();


    view_processingPresenter(view_processingContract.view_processingView view){
        this.mView = view;
        this.context = (Context) mView;


    }

    @Override
    public void get_processing_orders() {
        //Object declaration
        localhost = lc.getLocalhost();

        //object declaration
        final ArrayList<String> my_order_id = new ArrayList<>();
        final ArrayList<String> my_order_status = new ArrayList<>();
        final ArrayList<String> my_order_customUsername = new ArrayList<>();
        final ArrayList<String> my_order_items = new ArrayList<>();
        final ArrayList<String> my_order_total = new ArrayList<>();

        final StringRequest get_processingOrders = new StringRequest(Request.Method.POST, localhost + main_orders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_processingOrdersRes",response);
                if(!response.contains("failed")){
                    if(!response.contains("no_data_found")){
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("order_data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                //get object string

                                //getString
                                my_order_id.add(jsonObject1.getString("my_order_id"));
                                my_order_status.add(jsonObject1.getString("my_order_status"));
                                my_order_customUsername.add(jsonObject1.getString("my_order_customUsername"));
                                my_order_items.add(jsonObject1.getString("my_order_items"));
                                my_order_total.add(jsonObject1.getString("my_order_total"));
                            }
                            mView.populate_process_order_list(my_order_id,my_order_status,my_order_customUsername,my_order_items,
                                    my_order_total);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("processOrder_except",e.toString());

                        }
                    }else{
                        Toast.makeText(context,"No Data Found",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"Failed to Query",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_processOrderError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_processingOrders");


                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_processingOrders);
    }
}
