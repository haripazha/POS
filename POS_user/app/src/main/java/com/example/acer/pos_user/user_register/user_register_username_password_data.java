package com.example.acer.pos_user.user_register;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_login.user_login;

import org.w3c.dom.Text;

public class user_register_username_password_data extends AppCompatActivity implements user_register_usernameContract.user_registerUsernameView{
    //mvp declaration
    user_register_usernamePresenter presenter;

    //object declaration
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout c_password;
    Button finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register_username_password_data);
        presenter = new user_register_usernamePresenter(this);

        //object declaratin
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.confirm_password);
        finish = findViewById(R.id.submit);

        //run this method when system start
        systemStart();

    }

    //run this method when system start
    public void systemStart(){
        //finish the registration
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.validateInputData(username.getEditText().getText().toString(),
                                            password.getEditText().getText().toString(),
                                            c_password.getEditText().getText().toString());
            }
        });
    }

    //go to login page after registering the customer
    @Override
    public void goToLogin() {
        Intent intent = new Intent(user_register_username_password_data.this,user_login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
