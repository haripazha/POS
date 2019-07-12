package com.example.acer.servicebookingsystem.user_register_view;

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

public class register_viewPresenter implements register_viewContract.register_presenter {
    //mvp declaration
    register_viewContract.register_view mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_register = localhost + "service_booking_system/android_php/registration/main_registration.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    register_viewPresenter(register_viewContract.register_view view){
        this.mView = view;
        this.context = (Context) mView;
    }

    @Override
    public void onSubmit(String name, String contact_number, String username, String password, String cpassword,String homeBarangay,
                         String homeCity,
                         String homeAddress,
                         String userType) {

        if(!name.equals("")&&!contact_number.equals("")&&!username.equals("")&&!password.equals("")&&!cpassword.equals("")
                &&!homeBarangay.equals("")&&!homeCity.equals("")&&!homeAddress.equals("")){

            if(password.equals(cpassword)){
                //Stored data


                verifyUser(name,contact_number,username,password,homeBarangay,homeCity,homeAddress,
                            userType);

            }else{
                Toast.makeText(context,"Password mismatch!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context,"Please fill up all the empty fields!",Toast.LENGTH_LONG).show();
        }
    }


    //validate If username already exists
    public void verifyUser(final String name, final String contact_number, final String username, final String password, final String homeBarangay, final String homeCity, final String homeAddress,
                           final String userType){
        //Object declaration
        localhost = lc.getLocalhost();

        StringRequest verifyRequest = new StringRequest(Request.Method.POST, localhost + main_register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 Log.d("verifyReq_res",response);
                 if(!response.contains("failed")){
                    if(response.contains("not_exists")){

                        //Storing user_id to local
                        SharedPreferences store_user_id = context.getSharedPreferences("registration_details", Context.MODE_PRIVATE);
                        SharedPreferences.Editor store_username_editor = store_user_id.edit();
                        store_username_editor.putString("name",name);
                        store_username_editor.putString("contact_number",contact_number);
                        store_username_editor.putString("username",username);
                        store_username_editor.putString("password",password);
                        store_username_editor.putString("homeBarangay",homeBarangay);
                        store_username_editor.putString("homeCity",homeCity);
                        store_username_editor.putString("homeAddress",homeAddress);
                        store_username_editor.putString("userType",userType);
                        store_username_editor.commit();


                        //Proceed to step 2
                        mView.toStepTwo();
                    }else if(response.contains("exists")){
                        Toast.makeText(context,"Username already Exists! Please choose another one",Toast.LENGTH_LONG).show();
                    }
                 }else{
                     Toast.makeText(context,"Failed to Process ",Toast.LENGTH_LONG).show();
                 }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("verifyReq_error",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","validate_user");
                hashMap.put("val_username",username);


                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(verifyRequest);

    }
}
