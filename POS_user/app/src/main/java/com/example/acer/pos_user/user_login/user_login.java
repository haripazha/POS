package com.example.acer.pos_user.user_login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_mainView.user_mainView;
import com.example.acer.pos_user.user_register.user_register;

public class user_login extends AppCompatActivity implements user_loginContract.user_loginView{
    //mvp declaration
    user_loginPresenter presenter;

    //object declaration
    TextView to_register;
    TextInputLayout username;
    TextInputLayout password;
    Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        presenter = new user_loginPresenter(this);

        //object declaration
        to_register = findViewById(R.id.register);
        login       = findViewById(R.id.login);
        username    = findViewById(R.id.username);
        password    = findViewById(R.id.password);

        //run this method when system start
        systemStart();
    }

    public void systemStart(){
        to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_login.this,user_register.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debug code
                username.getEditText().setText("mark");
                password.getEditText().setText("123");

                presenter.verifyCustomer(username.getEditText().getText().toString(),
                                        password.getEditText().getText().toString());
            }
        });
    }

    //Go To Main
    @Override
    public void goToMain() {
        Intent intent = new Intent(user_login.this,user_mainView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
