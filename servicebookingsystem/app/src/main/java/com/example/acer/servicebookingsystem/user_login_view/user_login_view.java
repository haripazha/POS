package com.example.acer.servicebookingsystem.user_login_view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.provider_home_view.provider_home_view;
import com.example.acer.servicebookingsystem.user_home_view.home_view;
import com.example.acer.servicebookingsystem.user_register_view.register_view;

public class user_login_view extends AppCompatActivity implements user_login_viewContract.user_loginView{
    //mvp declaration
    user_login_viewPresenter presenter;


    //container
    LinearLayout loading_container;
    LinearLayout login_container;

    TextInputLayout username;
    TextInputLayout password;

    Button login;
    Button register;

    //location declaration
    LocationManager locationManager;

    //Request code declaration
    private static  final int REQUEST_LOCATION=1;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_view);
        presenter = new user_login_viewPresenter(this);

        //object declaration
        loading_container   = findViewById(R.id.loading_container);
        login_container     = findViewById(R.id.login_container);
        username            = findViewById(R.id.username);
        password            = findViewById(R.id.password);
        login               = findViewById(R.id.login);
        register            = findViewById(R.id.to_register);
        pd                  = new ProgressDialog(user_login_view.this);

        //run this method when systemStart
        systemStart();
    }

    //system start method
    public void systemStart(){
        loading_container.setVisibility(View.VISIBLE);
        login_container.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                login_container.setVisibility(View.VISIBLE);
                loading_container.setVisibility(View.GONE);

                //enable gps
                checkIfGPSON();
            }
        },2500);

        //to_login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.getEditText().setText("admin");
                password.getEditText().setText("admin");
                pd.setMessage("Logging In...");
                pd.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.toLogin(username.getEditText().getText().toString(),password.getEditText().getText().toString());
                    }
                },1500);

            }
        });

        //to register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_login_view.this,register_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });



    }

    public void checkIfGPSON(){

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager=(LocationManager) getSystemService(getApplication().LOCATION_SERVICE);

        //Check gps is enable or not
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            //Write Function To enable gps
            presenter.enableGPS();
        }
        else
        {

        }

    }

    @Override
    public void onLoginSuccess(String role) {
        pd.hide();

        Log.d("onLoginSu",role);


        //conditional of role
        if(role.contains("User")){
            Intent intent = new Intent(user_login_view.this, home_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }else if(role.contains("Service Provider")){
            Intent intent = new Intent(user_login_view.this, provider_home_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onFailedLogin() {
        pd.hide();
    }
}
