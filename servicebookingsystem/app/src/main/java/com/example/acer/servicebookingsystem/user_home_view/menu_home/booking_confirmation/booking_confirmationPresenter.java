package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_confirmation;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class booking_confirmationPresenter implements booking_confirmationContract.booking_presenter {

    //mvp declaration
    booking_confirmationContract.booking_view mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_provider_view = localhost + "service_booking_system/android_php/provider_home_view/booking.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();

    booking_confirmationPresenter(booking_confirmationContract.booking_view view){
        this.mView = view;
        this.context = (Context) mView;

    }

    @Override
    public void get_booking_details() {
        //Object declaration
        localhost = lc.getLocalhost();

        //get booking id from local storage
        //get the session username of the user
        SharedPreferences get_deviceID = context.getSharedPreferences("booking_service_id", Context.MODE_PRIVATE);
        final String booking_id               = get_deviceID.getString("booking_id","");

        final String[] service_id = {""};
        final String[] service_company = {""};
        final String[] service_name = {""};
        final String[] service_description = {""};
        final String[] service_rate = {""};
        String service_type         = "";
        String service_image        = "";
        String service_status       = "";
        String service_postedDay    = "";
        String service_postedMonth  = "";
        String service_postedYear   = "";


        StringRequest getBooking_details = new StringRequest(Request.Method.POST, localhost + main_provider_view, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getBooking_res",response);
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("booking_data");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject ajson = jsonArray.getJSONObject(i);

                            //get json object
                            service_id[0]       = ajson.getString("service_id");
                            service_company[0]  = ajson.getString("service_company");
                            service_name[0]     = ajson.getString("service_name");
                            service_description[0] = ajson.getString("service_description");
                            service_rate[0] = ajson.getString("service_rate");


                        }

                    } catch (JSONException e) {
                        Log.d("getBooking_exception",e.toString());
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context,"Failed to process query",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getBooking_error",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getBooking_details");
                hashMap.put("getBooking_details_id",booking_id);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getBooking_details);
    }
}
