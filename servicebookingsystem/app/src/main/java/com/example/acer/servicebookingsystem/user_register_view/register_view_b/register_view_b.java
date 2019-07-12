package com.example.acer.servicebookingsystem.user_register_view.register_view_b;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_login_view.user_login_view;

public class register_view_b extends AppCompatActivity implements register_view_bContract.register_view{
    //mvp declaration
    register_view_bPresenter presenter;

    //object declaration
    ImageView back_button;

    Button accept;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view_b);
        presenter = new register_view_bPresenter(this);

        //object registratin
        back_button = findViewById(R.id.back_button);
        accept      = findViewById(R.id.accept);
        pd          = new ProgressDialog(register_view_b.this);

        //run this method when system start
        systemStart();
    }

    public void systemStart(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                pd.setMessage("Finishing Your Registration");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.onTermsAccepted();
                    }
                },1000);
            }
        });


    }

    @Override
    public void onRegisterSuccess() {
        pd.hide();
        Intent intent = new Intent(register_view_b.this,user_login_view.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterFailed() {
        //close dialog
        pd.hide();
    }
}
