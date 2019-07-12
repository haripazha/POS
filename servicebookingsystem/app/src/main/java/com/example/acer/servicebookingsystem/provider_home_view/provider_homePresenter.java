package com.example.acer.servicebookingsystem.provider_home_view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.acer.servicebookingsystem.background_services.background_services;
import com.example.acer.servicebookingsystem.localhost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class provider_homePresenter implements provider_homeContract.provider_homePresenter {

    //mvp declaration
    provider_homeContract.provider_homeView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_view = localhost + "service_booking_system/android_php/user_home_view/main_home_view.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    //object declaration
    String user_id              = "";
    String user_name            = "";
    String user_homeAddress     = "";
    String user_homeCity        = "";
    String user_homeBarangay    = "";
    String user_role            = "";
    String user_username        = "";
    String user_email           = "";
    String user_profile_img     = "";
    String user_created         = "";

    provider_homePresenter(provider_homeContract.provider_homeView view){
        this.mView = view;
        this.context = (Context) mView;
    }

    //run this when user logout
    @Override
    public void onLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Prompt");
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pd = new ProgressDialog(context);
                        pd.setMessage("Please Wait");
                        pd.show();
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                pd.hide();
                                //stop service
                                context.stopService(new Intent(context,background_services.class));
                                mView.onExit();

                            }
                        }, 2000);

                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void getUser_details() {
        //Object declaration
        localhost = lc.getLocalhost();


        //get the session username of the user
        SharedPreferences get_deviceID = context.getSharedPreferences("username_session", Context.MODE_PRIVATE);
        final String username               = get_deviceID.getString("username","");

        StringRequest getUser_details = new StringRequest(Request.Method.POST, localhost + main_view, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getUser_DetailsRes",response);
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("user_data");
                        for(int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //get String
                            user_name = jsonObject1.getString("user_name");
                            user_email = jsonObject1.getString("user_email");
                            user_profile_img = jsonObject1.getString("user_profile_img");
                        }

                        //stored data to local storage
                        SharedPreferences store_user_data = context.getSharedPreferences("store_user_data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor store_username_editor = store_user_data.edit();
                        store_username_editor.putString("user_name",user_name);
                        store_username_editor.putString("user_email",user_email);
                        store_username_editor.putString("user_profile_img",user_profile_img);
                        store_username_editor.commit();

                        mView.populateDrawableContent(user_name,user_email,user_profile_img);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("getUser_DetailsExcept",e.toString());
                    }
                }else{
                    Toast.makeText(context,"Failed to retrieve data!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getUser_DetailsError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();

                //get the session username of the user
                SharedPreferences get_deviceID = context.getSharedPreferences("store_user_data", Context.MODE_PRIVATE);
                final String user_name         = get_deviceID.getString("user_name","");
                final String user_email        = get_deviceID.getString("user_email","");
                final String user_profile_img  = get_deviceID.getString("user_profile_img","");

                mView.populateDrawableContent(user_name,user_email,user_profile_img);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getUser_Details");
                hashMap.put("getDetails_username",username);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getUser_details);
    }
}
