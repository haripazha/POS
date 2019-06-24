package com.example.acer.pos_user.user_register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.pos_user.R;

public class user_register extends AppCompatActivity implements user_registerContract.userReg_view{
    //Mvp declaration
    user_registerPresenter presenter;


    //object declaration
    TextInputLayout name;
    TextInputLayout address;
    TextInputLayout contact_number;
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        presenter = new user_registerPresenter(this);

        //object declaration
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        contact_number = findViewById(R.id.contact_number);
        next = findViewById(R.id.next);

        //system start
        systemStart();
    }

    //run this method when system start
    public void systemStart(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getEditText().getText().toString().equals("")&&!address.getEditText().getText().toString().equals("")&&!contact_number.getEditText().getText().toString().equals("")){
                    //stored the details to local storage
                    presenter.storedCustomer_Details(name.getEditText().getText().toString(),
                                                    address.getEditText().getText().toString(),
                                                    contact_number.getEditText().getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(),"Please fill up all the fields!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void toUsername_password_data() {
        Intent intent = new Intent(user_register.this,user_register_username_password_data.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    }
}
