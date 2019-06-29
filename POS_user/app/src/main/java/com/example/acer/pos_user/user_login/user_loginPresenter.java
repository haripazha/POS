package com.example.acer.pos_user.user_login;

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


public class user_loginPresenter implements user_loginContract.user_loginPresenter {
    //Mvp declaraion
    user_loginContract.user_loginView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_login = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_login/pos_login.php";
    com.example.acer.pos_user.localhost lc = new localhost();

    //object declaration
    ProgressDialog pd;



    user_loginPresenter(user_loginContract.user_loginView view){
        this.mView = view;
        this.context = (Context) mView;
        this.pd = new ProgressDialog(context);
    }


    //verify customer username and password
    @Override
    public void verifyCustomer(final String username, final String password) {
        if(!username.equals("")&&!password.equals("")){
            pd.setMessage("Please wait...");
            pd.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    verifyIfCustomerExists(username,password);
                }
            },1000);


        }else {
            Toast.makeText(context,"Please fill up all the empty fields!",Toast.LENGTH_LONG).show();
        }
    }

    public void verifyIfCustomerExists(final String username, final String password){
        //Object declaration
        localhost = lc.getLocalhost();

        StringRequest verifyCustomer = new StringRequest(Request.Method.POST, localhost + main_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("verifyCus_res",response.toString());
                pd.hide();
                if(!response.contains("failed")){
                    if(!response.contains("customer_notExists")){
                        if(response.contains("customer_exists")){

                            //Store username to local storage
                            //Store deviceId/cart id
                            SharedPreferences store_home_sale_state = context.getSharedPreferences("customer_username", Context.MODE_PRIVATE);
                            SharedPreferences.Editor store_home_sale_stateEdit = store_home_sale_state.edit();
                            store_home_sale_stateEdit.putString("customer_username",username);
                            store_home_sale_stateEdit.commit();

                            //Go To Main
                            mView.goToMain();
                        }
                    }else {
                        Toast.makeText(context,"Customer doesn't exists",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(context,"Failed to Verify User",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.hide();
                Log.d("verifyCus_error",error.toString());
                Toast.makeText(context,"Connection Problem", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","loginCustomer");
                hashMap.put("login_username",username);
                hashMap.put("login_password",password);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(verifyCustomer);
    }
}
