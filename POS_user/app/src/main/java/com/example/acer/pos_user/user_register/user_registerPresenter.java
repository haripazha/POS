package com.example.acer.pos_user.user_register;

import android.content.Context;
import android.content.SharedPreferences;

public class user_registerPresenter implements user_registerContract.userReg_presenter {
    //mvp declaration
    user_registerContract.userReg_view mView;
    Context context;


    user_registerPresenter(user_registerContract.userReg_view view){
        this.mView = view;
        this.context = (Context) mView;
    }

    @Override
    public void storedCustomer_Details(String name, String address, String contact_number) {
        //Stored customer details name, address, contact to local storrage
        SharedPreferences store_customer_details = context.getSharedPreferences("customer_temp_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor store_customer_detailsEdit = store_customer_details.edit();
        store_customer_detailsEdit.putString("name",name);
        store_customer_detailsEdit.putString("address",address);
        store_customer_detailsEdit.putString("contact_number",contact_number);
        store_customer_detailsEdit.commit();

        //go to next module
        mView.toUsername_password_data();
    }
}
