package com.example.acer.servicebookingsystem.user_register_view.register_view_b;

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

public class register_view_bPresenter implements register_view_bContract.register_presenter {
    //mvp declaration
    register_view_bContract.register_view mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_register = localhost + "service_booking_system/android_php/registration/main_registration.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    register_view_bPresenter(register_view_bContract.register_view view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void onTermsAccepted() {
        //Object declaration
        localhost = lc.getLocalhost();

        //get all the data of the users
        SharedPreferences get_deviceID = context.getSharedPreferences("registration_details", Context.MODE_PRIVATE);
        final String name               = get_deviceID.getString("name","");
        final String contact_number     = get_deviceID.getString("contact_number","");
        final String username           = get_deviceID.getString("username","");
        final String password           = get_deviceID.getString("password","");
        final String homeBarangay       = get_deviceID.getString("homeBarangay","");
        final String homeCity           = get_deviceID.getString("homeCity","");
        final String homeAddress        = get_deviceID.getString("homeAddress","");
        final String userType           = get_deviceID.getString("userType","");
        final String image_string       = get_deviceID.getString("image_string","");
        final String email              = get_deviceID.getString("email","");
        final String valid_id           = get_deviceID.getString("valid_id","");
        final String latitude           = get_deviceID.getString("latitude","");
        final String longitude          = get_deviceID.getString("longitude","");
        final String company_num_employee           = get_deviceID.getString("company_num_employee","");
        final String company_address                = get_deviceID.getString("company_address","");
        final String company_barangay               = get_deviceID.getString("company_barangay","");
        final String company_service                = get_deviceID.getString("company_service","");
        final String company_city                   = get_deviceID.getString("company_city","");
        final String company_name                   = get_deviceID.getString("company_name","");
        final String company_description                   = get_deviceID.getString("company_description","");

        //Debug
        Log.d("FOUNDBUG",homeBarangay+" "+email);

        StringRequest upload_user = new StringRequest(Request.Method.POST, localhost + main_register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //close dialog
                mView.onRegisterFailed();

                Log.d("upload_userRes",response);
                if (!response.contains("failed")) {
                    if(response.contains("success")){
                        mView.onRegisterSuccess();
                    }
                }else{
                    Toast.makeText(context,"Failed to process the data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mView.onRegisterFailed();
                Log.d("upload_userErr",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","register_user");
                hashMap.put("reg_name",name);
                hashMap.put("reg_contact_number",contact_number);
                hashMap.put("reg_username",username);
                hashMap.put("reg_password",password);
                hashMap.put("reg_image_string",image_string);
                hashMap.put("reg_email",email);
                hashMap.put("reg_homebarangay",homeBarangay);
                hashMap.put("reg_homeCity",homeCity);
                hashMap.put("reg_homeAddress",homeAddress);
                hashMap.put("reg_userType",userType);
                hashMap.put("reg_validID",valid_id);
                hashMap.put("reg_latitude",latitude);
                hashMap.put("reg_longitude",longitude);

                //company details
                hashMap.put("reg_company_employees",company_num_employee);
                hashMap.put("reg_company_address",company_address);
                hashMap.put("reg_company_barangay",company_barangay);
                hashMap.put("reg_company_service",company_service);
                hashMap.put("reg_company_city",company_city);
                hashMap.put("reg_company_name",company_name);
                hashMap.put("reg_company_description",company_description);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(upload_user);
    }
}
