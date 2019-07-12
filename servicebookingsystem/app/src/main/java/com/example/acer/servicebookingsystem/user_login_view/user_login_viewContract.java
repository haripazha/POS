package com.example.acer.servicebookingsystem.user_login_view;

public interface user_login_viewContract {
    interface user_loginView{
        void onLoginSuccess(String role);
        void onFailedLogin();
    }
    interface user_loginPresenter{
        void toLogin(String username,String password);
        void enableGPS();
    }
}
