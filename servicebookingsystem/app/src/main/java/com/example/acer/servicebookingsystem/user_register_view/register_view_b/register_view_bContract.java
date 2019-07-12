package com.example.acer.servicebookingsystem.user_register_view.register_view_b;

public interface register_view_bContract {
    interface register_view{
        void onRegisterSuccess();
        void onRegisterFailed();
    }
    interface register_presenter{
        void onTermsAccepted();
    }
}
