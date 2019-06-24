package com.example.acer.pos_user.user_login;

public interface user_loginContract {

    interface user_loginView{
        void goToMain();

    }
    interface user_loginPresenter{
        void verifyCustomer(String username,String password);
    }
}
