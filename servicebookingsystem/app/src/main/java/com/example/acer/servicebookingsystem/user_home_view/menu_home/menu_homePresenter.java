package com.example.acer.servicebookingsystem.user_home_view.menu_home;

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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class menu_homePresenter implements menu_homeContract.menu_homePresenter {
    //mvp declaration
    menu_homeContract.menu_homeView mView;

    //network address Declaration
    String localhost = "";
    String main_view = localhost + "service_booking_system/android_php/user_home_view/main_home_view.php";
    com.example.acer.servicebookingsystem.localhost lc = new localhost();


    menu_homePresenter(menu_homeContract.menu_homeView view){
        this.mView = view;
    }

    @Override
    public void getServiceList(final Context context) {
        //Object declaration
        localhost = lc.getLocalhost();

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

        final StringRequest getService_list = new StringRequest(Request.Method.POST, localhost + main_view, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getService_listRes",response);
                if(!response.contains("failed")){
                        try{
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("service_list");
                               for(int i = 0; i < jsonArray.length(); i++ ){
                                   JSONObject ajson = jsonArray.getJSONObject(i);

                                   //get string
                                   service_id.add(ajson.getString("service_id"));
                                   service_company.add(ajson.getString("service_company"));
                                   service_name.add(ajson.getString("service_name"));
                                   service_description.add(ajson.getString("service_description"));
                                   service_rate.add(ajson.getString("service_rate"));
                                   service_type.add(ajson.getString("service_type"));
                                   service_image.add(ajson.getString("service_image"));
                                   service_status.add(ajson.getString("service_status"));
                                   service_postedDay.add(ajson.getString("service_postedDay"));
                                   service_postedMonth.add(ajson.getString("service_postedMonth"));
                                   service_postedYear.add(ajson.getString("service_postedYear"));

                               }

                               if(service_id.size()>0){

                               }else{

                               }

                               //store to local storage
                            saveData(context,service_id,
                                    service_company,
                                    service_name,
                                    service_description,
                                    service_rate,
                                    service_type,
                                    service_image,
                                    service_status,
                                    service_postedDay,
                                    service_postedMonth,
                                    service_postedYear);

                        }catch(JSONException e) {
                            e.printStackTrace();
                            Log.d("getService_listExcept",e.toString());
                        }

                }else{
                    Toast.makeText(context,"Failed to process query",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getService_listErr",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
                loadData(context);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","get_service_list");

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getService_list);
    }


    //save data
    private void saveData(Context context,ArrayList<String> id,
                          ArrayList<String> service_company,
                          ArrayList<String> service_name,
                          ArrayList<String> service_description,
                          ArrayList<String> service_rate,
                          ArrayList<String> service_type,
                          ArrayList<String> service_image,
                          ArrayList<String> service_status,
                          ArrayList<String> service_postedDay,
                          ArrayList<String> service_postedMonth,
                          ArrayList<String> service_postedYear) {

        Log.d("local_storage",service_name.toString());

        //Service ID
        StringBuilder string_service_id = new StringBuilder();
        for(String s : id){
            string_service_id.append(s);
            string_service_id.append(",");
        }
        //service company
        StringBuilder String_service_company = new StringBuilder();
        for(String s : service_company){
            String_service_company.append(s);
            String_service_company.append(",");
        }
        //service name
        StringBuilder string_service_name = new StringBuilder();
        for(String s : service_name){
            string_service_name.append(s);
            string_service_name.append(",");
        }
        //service description
        StringBuilder string_service_description = new StringBuilder();
        for(String s : service_description){
            string_service_description.append(s);
            string_service_description.append(",");
        }
        //service rate
        StringBuilder string_service_rate = new StringBuilder();
        for(String s : service_rate){
            string_service_rate.append(s);
            string_service_rate.append(",");
        }
        //service rate
        StringBuilder string_service_type = new StringBuilder();
        for(String s : service_type){
            string_service_type.append(s);
            string_service_type.append(",");
        }
        //service image
        StringBuilder string_service_image = new StringBuilder();
        for(String s : service_image){
            string_service_image.append(s);
            string_service_image.append(",");
        }
        //service status
        StringBuilder string_service_status = new StringBuilder();
        for(String s : service_status){
            string_service_status.append(s);
            string_service_status.append(",");
        }
        //service posted day
        StringBuilder string_service_postedDay = new StringBuilder();
        for(String s : service_postedDay){
            string_service_postedDay.append(s);
            string_service_postedDay.append(",");
        }
        //service posted month
        StringBuilder string_service_postedMonth = new StringBuilder();
        for(String s : service_postedMonth){
            string_service_postedMonth.append(s);
            string_service_postedMonth.append(",");
        }
        //service posted year
        StringBuilder string_service_postedYear = new StringBuilder();
        for(String s : service_postedYear){
            string_service_postedYear.append(s);
            string_service_postedYear.append(",");
        }


        Log.d("string_service_name",string_service_name.toString());

        //Stored to local storage
        SharedPreferences settings = context.getSharedPreferences("service_list",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("service_id",string_service_id.toString());
        editor.putString("service_company",String_service_company.toString());
        editor.putString("service_name",string_service_name.toString());
        editor.putString("service_description",string_service_description.toString());
        editor.putString("service_rate",string_service_rate.toString());
        editor.putString("service_type",string_service_type.toString());
        editor.putString("service_image",string_service_image.toString());
        editor.putString("service_status",string_service_status.toString());
        editor.putString("service_postedDay",string_service_postedDay.toString());
        editor.putString("service_postedMonth",string_service_postedMonth.toString());
        editor.putString("service_postedYear",string_service_postedYear.toString());
        editor.commit();


        loadData(context);
    }

    //read local data
    private void loadData(Context context) {

        SharedPreferences settings  = context.getSharedPreferences("service_list",0);
        String service_id           = settings.getString("service_id","");
        String service_company      = settings.getString("service_company","");
        String service_name         = settings.getString("service_name","");
        String service_description  = settings.getString("service_description","");
        String service_rate         = settings.getString("service_rate","");
        String service_type         = settings.getString("service_type","");
        String service_image        = settings.getString("service_image","");
        String service_status       = settings.getString("service_status","");
        String service_postedDay    = settings.getString("service_postedDay","");
        String service_postedMonth  = settings.getString("service_postedMonth","");
        String service_postedYear   = settings.getString("service_postedYear","");

        String[]  service_id_items          = service_id.split(",");
        String[]  service_company_items     = service_company.split(",");
        String[]  service_name_items        = service_name.split(",");
        String[]  service_description_items = service_description.split(",");
        String[]  service_rate_items        = service_rate.split(",");
        String[]  service_type_items        = service_type.split(",");
        String[]  service_image_items       = service_image.split(",");
        String[]  service_status_items      = service_status.split(",");
        String[]  service_postedDay_items   = service_postedDay.split(",");
        String[]  service_postedMonth_items = service_postedMonth.split(",");
        String[]  service_postedYear_items  = service_postedYear.split(",");

        ArrayList<String> stored_service_id             = new ArrayList<String>();
        ArrayList<String> stored_service_company        = new ArrayList<>();
        ArrayList<String> stored_service_name           = new ArrayList<>();
        ArrayList<String> stored_service_description    = new ArrayList<>();
        ArrayList<String> stored_service_rate           = new ArrayList<>();
        ArrayList<String> stored_service_type           = new ArrayList<>();
        ArrayList<String> stored_service_image          = new ArrayList<>();
        ArrayList<String> stored_service_status         = new ArrayList<>();
        ArrayList<String> stored_service_postedDay      = new ArrayList<>();
        ArrayList<String> stored_service_postedMonth    = new ArrayList<>();
        ArrayList<String> stored_service_postedYear     = new ArrayList<>();



        //company id
        for(int i = 0; i < service_id_items.length; i++){
            stored_service_id.add(service_id_items[i]);
        }
        //company items
        for(int i = 0; i < service_company_items.length; i++){
            stored_service_company.add(service_company_items[i]);
        }

        //comapany name items
        for(int i = 0; i < service_name_items.length; i++){
            stored_service_name.add(service_name_items[i]);
        }
        //service description
        for(int i = 0; i < service_description_items.length; i++){
            stored_service_description.add(service_description_items[i]);
        }
        //service rate
        for(int i = 0; i < service_rate_items.length; i++){
            stored_service_rate.add(service_rate_items[i]);
        }
        //service type
        for(int i = 0; i < service_type_items.length; i++){
            stored_service_type.add(service_type_items[i]);
        }
        //service image item
        for(int i = 0; i < service_image_items.length; i++){
            stored_service_image.add(service_image_items[i]);
        }
        //service status item
        for(int i = 0; i < service_status_items.length; i++){
            stored_service_status.add(service_status_items[i]);
        }
        //service postedDay
        for(int i = 0; i < service_postedDay_items.length; i++){
            stored_service_postedDay.add(service_postedDay_items[i]);
        }
        //service postedMonth
        for(int i = 0; i < service_postedMonth_items.length; i++){
            stored_service_postedMonth.add(service_postedMonth_items[i]);
        }
        //service postedYear
        for(int i = 0; i < service_postedYear_items.length; i++){
            stored_service_postedYear.add(service_postedYear_items[i]);
        }


        Log.d("stored_service_id",stored_service_name.toString()+" "+stored_service_name.size());


            mView.populateRecycler_view(stored_service_id,stored_service_company,stored_service_name,
                    stored_service_description,stored_service_rate,stored_service_type,stored_service_image,
                    stored_service_status,stored_service_postedDay,stored_service_postedMonth,stored_service_postedYear);




    }
}
