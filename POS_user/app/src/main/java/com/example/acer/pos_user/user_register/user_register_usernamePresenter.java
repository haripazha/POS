package com.example.acer.pos_user.user_register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pos_user.localhost;

import java.util.HashMap;
import java.util.Map;

public class user_register_usernamePresenter implements  user_register_usernameContract.user_registerusernamePresenter {

    //Mvp declaration
    user_register_usernameContract.user_registerUsernameView mView;
    Context context;

    //Object declaration
    ProgressDialog pd;


    //network address Declaration
    String localhost = "";
    String main_register = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_register/pos_register.php";
    localhost lc = new localhost();

    user_register_usernamePresenter(user_register_usernameContract.user_registerUsernameView view){
        this.mView = view;
        this.context = (Context) mView;
    }

    //validate user input
    @Override
    public void validateInputData(final String username, final String password, String c_password) {
        //object declaration
        pd = new ProgressDialog(context);


        if(!username.equals("")&&!password.equals("")){
            if(password.equals(c_password)){

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.show();
                        pd.setMessage("Please wait..");
                        ifValidated(username,password);
                    }
                },1000);

            }else {
                Toast.makeText(context,"Password mismatch!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context,"Please fill up empty fields!",Toast.LENGTH_LONG).show();
        }
    }

    // validated user input
    public void ifValidated(final String username, final String password){
        //Object declaration
        localhost = lc.getLocalhost();


        //get the value for start and end data
        SharedPreferences get_deviceID = context.getSharedPreferences("customer_temp_details", Context.MODE_PRIVATE);
        final String name = get_deviceID.getString("name","");
        final String address = get_deviceID.getString("address","");
        final String contact_number = get_deviceID.getString("contact_number","");

        StringRequest register_customer = new StringRequest(Request.Method.POST, localhost + main_register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.hide();
                Log.d("reg_customRes",response.toString());
                if(!response.contains("failed")){
                    if(response.contains("customer_exists")){
                        Toast.makeText(context,"Username exists!, Please choose another username",Toast.LENGTH_LONG).show();
                    }else{
                        if(response.contains("success")){
                            // Go to login page
                            mView.goToLogin();
                        }
                    }
                }else {
                    Toast.makeText(context,"Failed to submit data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.hide();
                Log.d("reg_customError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","Insert_customer");
                hashMap.put("insert_cusName",name);
                hashMap.put("insert_cusAddress",address);
                hashMap.put("insert_cusContact",contact_number);
                hashMap.put("insert_cusUsername",username);
                hashMap.put("insert_cusPassword",password);

                return  hashMap;

            }
        };
        Volley.newRequestQueue(context).add(register_customer);

    }

}
