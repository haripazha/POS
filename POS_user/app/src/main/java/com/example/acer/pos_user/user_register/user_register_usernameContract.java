package com.example.acer.pos_user.user_register;

public interface user_register_usernameContract {
    interface user_registerUsernameView{
        void goToLogin();

    }
    interface  user_registerusernamePresenter{
        void validateInputData(String username,String password,String c_password);

    }
}
