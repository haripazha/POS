package com.example.acer.servicebookingsystem.provider_home_view.menu_service;

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
import com.example.acer.servicebookingsystem.localhost;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class menu_servicePresenter implements menu_serviceContract.menu_servicePresenter {

    //mvp declaration
    menu_serviceContract.menu_serviceVIew mView;

    //network address Declaration
    String localhost = "";
    String main_provider_view = localhost + "service_booking_system/android_php/provider_home_view/menu_services.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    menu_servicePresenter(menu_serviceContract.menu_serviceVIew view){
        this.mView = view;

    }

    @Override
    public void getLatestServices_list(final Context context) {
        //Object declaration
        localhost = lc.getLocalhost();

        //get username from local storage
        SharedPreferences get_deviceID = context.getSharedPreferences("username_session", MODE_PRIVATE);
        final String username               = get_deviceID.getString("username","");


        Log.d("username_session",username);

        //object declaration
        final ArrayList<String> service_id            = new ArrayList<>();
        final ArrayList<String> service_company       = new ArrayList<>();
        final ArrayList<String> service_name          = new ArrayList<>();
        final ArrayList<String> service_description   = new ArrayList<>();
        final ArrayList<String> service_rate          = new ArrayList<>();
        final ArrayList<String> service_type          = new ArrayList<>();
        final ArrayList<String> service_image         = new ArrayList<>();
        final ArrayList<String> service_status        = new ArrayList<>();
        final ArrayList<String> service_postedDay     = new ArrayList<>();
        final ArrayList<String> service_postedMonth   = new ArrayList<>();
        final ArrayList<String> service_postedYear    = new ArrayList<>();

        //get my service list
        StringRequest get_serviceList = new StringRequest(Request.Method.POST, localhost + main_provider_view, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_serviceListRes",response);
                if(!response.contains("failed")){

                    if(!response.contains("not_exists")){

                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("service_list");
                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject ajsonObject1 = jsonArray.getJSONObject(i);

                                //get json string
                                service_id.add(ajsonObject1.getString("service_id"));
                                service_company.add(ajsonObject1.getString("service_company"));
                                service_name.add(ajsonObject1.getString("service_name"));
                                service_description.add(ajsonObject1.getString("service_description"));
                                service_rate.add(ajsonObject1.getString("service_rate"));
                                service_type.add(ajsonObject1.getString("service_type"));
                                service_image.add(ajsonObject1.getString("service_image"));
                                service_status.add(ajsonObject1.getString("service_status"));
                                service_postedDay.add(ajsonObject1.getString("service_postedDay"));
                                service_postedMonth.add(ajsonObject1.getString("service_postedMonth"));
                                service_postedYear.add(ajsonObject1.getString("service_postedYear"));

                            }



                            mView.populateList(service_id,service_company,service_name,service_description,service_rate,service_type,
                                    service_image,service_status,service_postedDay,service_postedMonth,service_postedYear);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(context,"You have No Existing Services!",Toast.LENGTH_LONG).show();
                    }




                }else{
                    Toast.makeText(context,"failed to retrive data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_serviceListErr",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_service");
                hashMap.put("get_service_username",username);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_serviceList);
    }
}
