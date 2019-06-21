package com.example.acer.pos_front_desk.main_view;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pos_front_desk.localhost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class main_viewPresenter implements main_viewContract.main_presenter {

    //Mvp declaration
    main_viewContract.main_view mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String pos_frontDesk_connect = localhost + "/MEATSHOP_POS_SALE/android_php/POS_main/POS_frontDesk_main/pos_frontDesk.php";
    localhost lc = new localhost();

    //variable declaration
    String item_name ="";
    String item_quantity ="";
    String item_price ="";
    String item_total ="";
    double sub_total = 0;


    main_viewPresenter(main_viewContract.main_view view){
        this.mView = view;
        this.context = (Context) mView;


    }

    //connect to pos
    @Override
    public void onConnectToMain() {
        //Object declaration
        localhost = lc.getLocalhost();

        //This generate the customer id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final String cart_id = sdf.format(new Date());

        //variable declaration
        item_name ="";
        item_quantity ="";
        item_price ="";
        item_total ="";
        sub_total = 0;


        StringRequest connectToPOS = new StringRequest(Request.Method.POST, localhost + pos_frontDesk_connect, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("connectToPOSResponse",response);
                if(!response.contains("failed")){
                    if(!response.contains("no_data")){
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("cart_data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                //convert data to double
                                sub_total = sub_total + Double.parseDouble(jsonObject1.getString("item_quantity")) * Double.parseDouble(jsonObject1.getString("item_price"));

                                //get String to json
                                item_name = item_name + jsonObject1.getString("item_name") + "\n";
                                item_quantity = item_quantity + jsonObject1.getString("item_quantity") + "\n";
                                item_price = item_price + jsonObject1.getString("item_price") + "\n";
                                item_total = item_total + jsonObject1.getString("item_total") + "\n";

                            }
                            mView.populate_the_textView(item_name,item_quantity,item_price,item_total,sub_total);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSONException",e.toString());
                        }
                    }else {
                        Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(context,"Failed to Connect!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("connectToPOSResError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","connectToPOS");
                hashMap.put("cart_id",cart_id);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(connectToPOS);


    }
}
