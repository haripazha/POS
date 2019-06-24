package com.example.acer.pos_user.user_mainView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

import com.example.acer.pos_user.user_login.user_login;

public class user_mainViewPresenter implements user_mainViewContract.user_mainPresenter {
    //mvp declaration
    user_mainViewContract.user_mainView mView;
    Context context;

    //object declaration
    ProgressDialog pd;


    user_mainViewPresenter(user_mainViewContract.user_mainView view){
        this.mView = view;
        this.context = (Context) mView;
    }

    //onLogout
    @Override
    public void onLogout() {
        //object declaration
        pd = new ProgressDialog(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Prompt");
        builder.setMessage("Do you want to sign out?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pd = new ProgressDialog(context);
                        pd.setMessage("Please Wait");
                        pd.show();
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                pd.hide();
                                mView.toLogin();
                            }
                        }, 2000);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
