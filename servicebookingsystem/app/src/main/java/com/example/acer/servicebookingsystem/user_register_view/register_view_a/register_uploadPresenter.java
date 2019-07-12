package com.example.acer.servicebookingsystem.user_register_view.register_view_a;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.servicebookingsystem.localhost;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class register_uploadPresenter implements register_uploadContract.register_uploadPresenter {
    //mvp declaration
    register_uploadContract.register_uploadView mView;
    Context context;



    register_uploadPresenter(register_uploadContract.register_uploadView view) {
        this.mView = view;
        this.context = (Context) mView;
    }

    @Override
    public void onSubmit(final String image_string, final String email, String valid_id_img_string,String latitude,
                         String longtitude,String company_numberOf_employee,
                         String company_address,
                         String company_barangay,
                         String company_service,
                         String company_city,
                         String company_name,
                         String company_description) {


        //Storing user_id to local
        SharedPreferences store_user_id = context.getSharedPreferences("registration_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor store_username_editor = store_user_id.edit();
        store_username_editor.putString("image_string", image_string);
        store_username_editor.putString("email", email);
        store_username_editor.putString("valid_id", email);
        store_username_editor.putString("latitude", latitude);
        store_username_editor.putString("longitude", longtitude);
        store_username_editor.putString("company_num_employee", company_numberOf_employee);
        store_username_editor.putString("company_address", company_address);
        store_username_editor.putString("company_barangay", company_barangay);
        store_username_editor.putString("company_service", company_service);
        store_username_editor.putString("company_city", company_city);
        store_username_editor.putString("company_name", company_name);
        store_username_editor.putString("company_description", company_description);
        store_username_editor.commit();

        mView.to_StepThree();

    }

    @Override
    public void validateUserType() {
        //get all the data of the users
        SharedPreferences get_userType = context.getSharedPreferences("registration_details", Context.MODE_PRIVATE);
        final String userType = get_userType.getString("userType", "");

        mView.showValidId_container(userType);
    }



    @Override
    public void enableGPS() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(context);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
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
}
