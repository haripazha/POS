package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.acer.pos_user.R;

public class generated_history extends AppCompatActivity implements generated_historyContract.generated_historyView{

    //mvp declaration
    generated_historyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_history);
        presenter = new generated_historyPresenter(this);


        //run this method when system start
        systemStart();

    }

    public void systemStart(){

    }
}
