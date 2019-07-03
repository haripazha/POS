package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history.generated_history_order_details;

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

import java.util.HashMap;
import java.util.Map;

public class generated_hisPresenter implements generated_hisContract.gen_hisPresenter {

    //mvp declaration
    generated_hisContract.gen_hisView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_orderHistory = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_orderHistory/pos_orderHistory.php";
    com.example.acer.pos_user.localhost lc = new localhost();

    // object declaration
    String my_order_id      = "";
    String my_order_status  = "";
    String my_order_customUsername = "";
    String my_order_items          = "";
    String my_order_total          = "";
    String my_order_day            = "";
    String my_order_month          = "";
    String my_order_year           = "";

    generated_hisPresenter(generated_hisContract.gen_hisView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void get_order_details() {
        //Object declaration
        localhost = lc.getLocalhost();

        //get the value for start and end data
        SharedPreferences get_deviceID = context.getSharedPreferences("history_order_id", Context.MODE_PRIVATE);
        final String order_id = get_deviceID.getString("history_order_id","");

        StringRequest get_order_details = new StringRequest(Request.Method.POST, localhost + main_orderHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_OrderDetailsRes",response);
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("order_details");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //get json string
                            my_order_id         = jsonObject1.getString("my_order_id");
                            my_order_status     = jsonObject1.getString("my_order_status");
                            my_order_items      = jsonObject1.getString("my_order_items");
                            my_order_total      = jsonObject1.getString("my_order_total");
                            my_order_day        = jsonObject1.getString("my_order_day");
                            my_order_month      = jsonObject1.getString("my_order_month");
                            my_order_year       = jsonObject1.getString("my_order_year");

                        }
                        mView.provide_details(my_order_id,my_order_status,
                                                my_order_items,my_order_total,
                                                my_order_day,my_order_month,my_order_year);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("get_order_dsException",e.toString());

                    }

                }else{
                    Toast.makeText(context,"Failed to retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_OrderDetailsError",error.toString());
                Toast.makeText(context,"Connection Problem",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_order_details");
                hashMap.put("get_orderDetails_id",order_id);


                return hashMap;

            }
      };
        Volley.newRequestQueue(context).add(get_order_details);


    }
}
