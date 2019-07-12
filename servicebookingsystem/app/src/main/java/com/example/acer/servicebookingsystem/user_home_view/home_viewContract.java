package com.example.acer.servicebookingsystem.user_home_view;

import java.util.ArrayList;

public interface home_viewContract {
    interface home_view{
        void onExit();
        void populateDrawableContent(String name,
                                     String email,
                                     String img_name);


    }
    interface home_presenter{
        void onLogout();
        void startService();
        void getUser_details();

        //Menu Item Function
        void onMenu_search();
    }
}
