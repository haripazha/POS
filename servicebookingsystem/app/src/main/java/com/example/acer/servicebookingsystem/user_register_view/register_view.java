package com.example.acer.servicebookingsystem.user_register_view;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_login_view.user_login_view;
import com.example.acer.servicebookingsystem.user_register_view.register_view_a.register_upload_photos;

public class register_view extends AppCompatActivity implements register_viewContract.register_view{

    //mvp declaration
    register_viewPresenter presenter;

    //object declaration
    ImageView back_button;

    Button next;

    Spinner userType;
    Spinner homeCity;

    TextInputLayout name;
    TextInputLayout contact_number;
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout cpassword;
    TextInputLayout homeAddress;
    TextInputLayout homeBarangay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        presenter = new register_viewPresenter(this);

        //object declaration
        back_button = findViewById(R.id.back_button);
        next        = findViewById(R.id.next);
        name        = findViewById(R.id.name);
        contact_number  = findViewById(R.id.contact_number);
        username        = findViewById(R.id.username);
        password        = findViewById(R.id.password);
        cpassword       = findViewById(R.id.c_password);
        userType        = findViewById(R.id.user_type);
        homeCity        = findViewById(R.id.city);
        homeAddress     = findViewById(R.id.home_address);
        homeBarangay    = findViewById(R.id.home_barangay);

        //run this methd when system start
        systemStart();
    }

    //system start
    public void systemStart(){
        //back pressed
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.onSubmit(name.getEditText().getText().toString(),
                                    contact_number.getEditText().getText().toString(),
                                    username.getEditText().getText().toString(),
                                    password.getEditText().getText().toString(),
                                    cpassword.getEditText().getText().toString(),
                                    homeBarangay.getEditText().getText().toString().toLowerCase(),
                                    homeCity.getSelectedItem().toString(),
                                    homeAddress.getEditText().getText().toString().toLowerCase(),
                                    userType.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void toStepTwo() {
        Intent intent = new Intent(register_view.this,register_upload_photos.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
