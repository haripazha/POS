package com.example.acer.pos_user.user_mainView;

import android.content.Context;

public interface user_mainViewContract {

    interface user_mainView{
        void toLogin();
    }

    interface user_mainPresenter{
        void onLogout();
    }
}
