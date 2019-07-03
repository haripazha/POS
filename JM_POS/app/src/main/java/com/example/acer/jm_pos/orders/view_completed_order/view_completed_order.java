package com.example.acer.jm_pos.orders.view_completed_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.acer.jm_pos.R;

public class view_completed_order extends AppCompatActivity implements view_completedContract.view_completedView{
    //mvp declaration
    view_completedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed_order);
        presenter = new view_completedPresenter(this);

    }
}
