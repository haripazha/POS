package com.example.acer.servicebookingsystem.provider_home_view;

public interface provider_homeContract {

    interface provider_homeView{
        void onExit();
        void populateDrawableContent(String name,
                                     String email,
                                     String img_name);
    }
    interface provider_homePresenter{
        void onLogout();
        void getUser_details();

    }
}
