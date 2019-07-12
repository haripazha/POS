package com.example.acer.servicebookingsystem.provider_home_view.menu_service.button_add_service;

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

import java.util.HashMap;
import java.util.Map;

public class add_servicePresenter implements add_serviceContract.add_servicePresenter {

    //mvp declaration
    add_serviceContract.add_serviceView mView;
    Context context;


    //network address Declaration
    String localhost = "";
    String main_provider_view = localhost + "service_booking_system/android_php/provider_home_view/add_service.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    add_servicePresenter(add_serviceContract.add_serviceView view){
        this.mView = view;
        this.context = (Context) mView;
    }

    //add service
    @Override
    public void add_service(String service_name, String service_description, String service_rate, String service_img_imgString,String service_type) {
        if(!service_img_imgString.equals("")){
            if(!service_name.equals("")&&!service_description.equals("")&&!service_rate.equals("")&&!service_type.equals("")){

                    //add company service
                        add_company_service(service_name,service_description,service_rate,service_img_imgString,service_type);

            }else{
                Toast.makeText(context,"Please fill up all the empty fields!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context,"Please Select image!",Toast.LENGTH_LONG).show();
        }
    }

    //add company service to database
    public void add_company_service(final String service_name, final String service_description, final String service_rate, final String service_img_imgString, final String service_type){
        //Object declaration
        localhost = lc.getLocalhost();

        //get username from local storage
        SharedPreferences get_deviceID = context.getSharedPreferences("username_session", Context.MODE_PRIVATE);
        final String username               = get_deviceID.getString("username","");

        Log.d("username_session",username);

        StringRequest add_service = new StringRequest(Request.Method.POST, localhost + main_provider_view, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("add_serviceRes",response);
                if(!response.contains("failed")){
                    Toast.makeText(context,"Your service has been added!",Toast.LENGTH_LONG).show();
                    mView.onInsertSuccess();
                }else{
                    mView.onInsertFailed();
                    Toast.makeText(context,"Failed to execute process!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mView.onInsertFailed();
                Log.d("add_serviceErr",error.toString());
                Toast.makeText(context,"Connection Provlem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","add_service");
                hashMap.put("add_service_name",service_name);
                hashMap.put("add_service_description",service_description);
                hashMap.put("add_service_rate",service_rate);
                hashMap.put("add_service_img_imgString",service_img_imgString);
                hashMap.put("add_service_type",service_type);
                hashMap.put("add_service_username",username);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(add_service);


    }
}
