package com.example.acer.servicebookingsystem.user_login_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
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

public class user_login_viewPresenter implements user_login_viewContract.user_loginPresenter {
    //mvp declaration
    user_login_viewContract.user_loginView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_login = localhost + "service_booking_system/android_php/login/main_login.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    user_login_viewPresenter(user_login_viewContract.user_loginView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void toLogin(final String username, final String password) {
        if(!username.equals("")&&!password.equals("")){

            //Object declaration
            localhost = lc.getLocalhost();


            //StringRequest
            StringRequest onLogin = new StringRequest(Request.Method.POST, localhost + main_login, new Response.Listener<String>() {
                @Override
                public void onResponse(String responses) {

                    String response = responses.replaceAll("\\s","");

                    Log.d("onLogin_res",response);

                    if(!response.contains("failed")){
                        if(response.contains("exists")){
                            //get user role
                            user_role(username);

                        }else if(response.contains("not_verified")){
                            Log.d("onResponseD","not_exists");
                            mView.onFailedLogin();
                            Toast.makeText(context,"It seems the account doesn't exists. Create a new one",Toast.LENGTH_LONG).show();
                        }else{
                            Log.d("onResponseD","can't find");
                            mView.onFailedLogin();
                        }
                    }else{
                        Log.d("onResponseD","failed to process");
                        mView.onFailedLogin();
                        Toast.makeText(context,"Failed to process",Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mView.onFailedLogin();
                    Log.d("onResponseD","connection error");
                    Log.d("onLogin_err",error.toString());
                    Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("function","onLogin");
                    hashMap.put("login_username",username);
                    hashMap.put("login_password",password);

                    return hashMap;
                }
            };
            Volley.newRequestQueue(context).add(onLogin);

        }else{
            Toast.makeText(context,"Please fill up the empty field!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void enableGPS() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(context);

        builder.setMessage("Our app need a location, Enable GPS?").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    //get user role
    public void user_role(final String username){
        //Object declaration
        localhost = lc.getLocalhost();


        StringRequest get_user_role = new StringRequest(Request.Method.POST, localhost + main_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("get_user_roleRes",response);
                if(!response.contains("failed")){
                    String role = response;

                    //Storing user_id to local
                    SharedPreferences store_user_id = context.getSharedPreferences("username_session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor store_username_editor = store_user_id.edit();
                    store_username_editor.putString("username",username);
                    store_username_editor.putString("user_role",role);
                    store_username_editor.commit();

                    //go to login status
                    mView.onLoginSuccess(role);

                }else{
                    Toast.makeText(context,"Failed to retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("get_user_roleError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_userRole");
                hashMap.put("get_userRole_username",username);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(get_user_role);
    }
}
