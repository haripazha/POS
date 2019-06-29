package com.example.acer.pos_user.user_mainView.fragment.my_order;

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

public class my_orderPresenter implements my_orderContract.my_orderPresenter {

    //mvp declaration
    my_orderContract.my_orderView mView;

    //network address Declaration
    String localhost = "";
    String main_MyOrder = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_myOrder/pos_myOrder.php";
    com.example.acer.pos_user.localhost lc = new localhost();

    //object declaration
    String itemDetails = "";
    String itemDate    = "";
    String itemStatus  = "";
    double itemTotal = 0;


    my_orderPresenter(my_orderContract.my_orderView view){
        this.mView = view;

    }

    @Override
    public void readMyOrders(final Context context) {
        //Object declaration
        localhost = lc.getLocalhost();

        //get the value of username
        SharedPreferences get_username = context.getSharedPreferences("customer_username", Context.MODE_PRIVATE);
        final String username = get_username.getString("customer_username","");

        //object declaration

        final ArrayList<String> order_id = new ArrayList<>();
        final ArrayList<String> my_order_status = new ArrayList<>();
        final ArrayList<String> my_order_items = new ArrayList<>();
        final ArrayList<String> my_order_total = new ArrayList<>();


        //get read my orders
        StringRequest getMyOrder = new StringRequest(Request.Method.POST, localhost + main_MyOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getMyOrderRes",response);
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("my_orders");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //get object string
                            order_id.add(jsonObject1.getString("my_order_id"));
                            my_order_status.add(jsonObject1.getString("my_order_status"));
                            my_order_items.add(jsonObject1.getString("my_order_items"));
                            my_order_total.add(jsonObject1.getString("my_order_total"));


                        }

                        //Populate the my_orderView
                        mView.populateMyOrderList(order_id,my_order_status,my_order_items,my_order_total);

                    } catch (JSONException e) {
                        Toast.makeText(context,"Exception ",Toast.LENGTH_LONG).show();
                        Log.d("JSONEXCeptMyOrder",e.toString());
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context,"failed to retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getMyOrderError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getMyOrders");
                hashMap.put("get_myOrderUsername",username);


                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getMyOrder);
    }
}
