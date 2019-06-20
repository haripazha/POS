package com.example.acer.jm_pos.reports.item_inventory.generate_inventoryBalance;

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

public class generate_invBalancePresenter implements generate_invBalanceContract.invBalance_presenter {

    //MVP delcaration
    generate_invBalanceContract.invBalance_view mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_inventoryBalance_report = localhost + "/MEATSHOP_POS_SALE/android_php/reports/report_inventory_balance/report_inventory_balance.php";
    com.example.acer.jm_pos.localhost lc = new localhost();

    //String
    String inventory_id = "";
    String inventory_item_name = "";
    String inventory_stock = "";
    String inventory_desc = "";
    String inventory_price  = "";


    generate_invBalancePresenter(generate_invBalanceContract.invBalance_view view){
        this.mView = view;
        this.context = (Context) mView;

    }


    @Override
    public void get_inventoryBalance() {
        //Object declaration
        localhost = lc.getLocalhost();




        StringRequest inventoryBalance = new StringRequest(Request.Method.POST, localhost + main_inventoryBalance_report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("inventoryBalanceRes",response.toString());
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("inventory_balanceData");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            inventory_id = inventory_id + jsonObject1.getString("inventory_id") +"\n";
                            inventory_item_name = inventory_item_name + jsonObject1.getString("inventory_item_name") +"\n";
                            inventory_stock = inventory_stock + jsonObject1.getString("stock") +"\n";
                            inventory_desc = inventory_desc + jsonObject1.getString("item_desc") + "\n";
                            inventory_price = inventory_price + jsonObject1.getString("item_price") + "\n";


                        }
                        mView.populateInventoryItem(inventory_id,inventory_item_name,inventory_stock,inventory_desc,inventory_price);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(context,"Failed to get Data",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("inventoryBalanceError",error.toString());
                Toast.makeText(context,"Connection Problem",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_inventoryBalance");

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(inventoryBalance);
    }
}
