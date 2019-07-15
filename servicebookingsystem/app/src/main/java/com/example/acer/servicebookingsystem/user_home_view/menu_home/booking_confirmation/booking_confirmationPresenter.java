package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_confirmation;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.servicebookingsystem.current_location.current_location;
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
        final String[] service_type = {""};
        final String[] service_image = {""};
        final String[] service_status = {""};
        final String[] service_postedDay = {""};
        final String[] service_postedMonth = {""};
        final String[] service_postedYear = {""};
        final String[] company_latitude = {""};
        final String[] company_longitude = {""};
        final String[] company_homeAddress = {""};
        final String[] company_homeBarangay = {""};
        final String[] company_homeCity = {""};

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
                            service_id[0]           = ajson.getString("service_id");
                            service_company[0]      = ajson.getString("service_company");
                            service_name[0]         = ajson.getString("service_name");
                            service_description[0]  = ajson.getString("service_description");
                            service_rate[0]         = ajson.getString("service_rate");
                            service_type[0]         = ajson.getString("service_type");
                            service_image[0]        = ajson.getString("service_image");
                            service_status[0]       = ajson.getString("service_status");
                            service_postedDay[0]    = ajson.getString("service_postedDay");
                            service_postedMonth[0]  = ajson.getString("service_postedMonth");
                            service_postedYear[0]   = ajson.getString("service_postedYear");
                            company_latitude[0] = ajson.getString("company_latitude");
                            company_longitude[0] = ajson.getString("company_longitude");
                            company_homeAddress[0] = ajson.getString("company_homeAddress");
                            company_homeBarangay[0] = ajson.getString("company_homeBarangay");
                            company_homeCity[0] = ajson.getString("company_homeCity");

                        }


                        //getting the current user location
                        current_location cl = new current_location();
                        String cl_lat = cl.getLatitude();
                        String cl_long = cl.getLongitude();
                        double metersToKm;


                        //get the session username of the user
                        SharedPreferences get_deviceID = context.getSharedPreferences("current_user_location", Context.MODE_PRIVATE);
                        final String current_lat                = get_deviceID.getString("current_latitude","");
                        final String current_long               = get_deviceID.getString("current_longitude","");

                        Log.d("company_latitudecords"," "+current_lat);

                        if(!current_lat.equals("")&&!current_long.equals("")){
                            //Calculating Distance
                            Location locationA = new Location("point A");
                            locationA.setLatitude(Float.parseFloat(current_lat));
                            locationA.setLongitude(Float.parseFloat(current_long));
                            Location locationB = new Location("point B");
                            locationB.setLatitude(Float.parseFloat(company_latitude[0]));
                            locationB.setLongitude(Float.parseFloat(company_longitude[0]));
                            double distance = locationA.distanceTo(locationB);
                            double metersRounding = distance;
                            metersToKm = Float.parseFloat(String.valueOf(String.format("%.2f",(distance * 1)/1000)));
                        }else{
                            metersToKm = 0;
                        }




                        mView.populateBookingDetails(service_id[0],service_company[0],service_name[0],service_description[0],service_rate[0]
                        ,service_type[0],service_image[0],service_status[0],service_postedDay[0],service_postedMonth[0],service_postedYear[0],
                                company_latitude[0],company_longitude[0],company_homeAddress[0],company_homeBarangay[0],company_homeCity[0],
                                String.valueOf(metersToKm));
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
