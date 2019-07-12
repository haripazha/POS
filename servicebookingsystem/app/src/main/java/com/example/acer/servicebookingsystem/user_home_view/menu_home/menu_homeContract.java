package com.example.acer.servicebookingsystem.user_home_view.menu_home;

import android.content.Context;

import java.util.ArrayList;

public interface menu_homeContract {
    interface menu_homeView{
        //populate recyclerview
        void populateRecycler_view(ArrayList<String> service_id,
                                   ArrayList<String> service_company_name,
                                   ArrayList<String> service_name,
                                   ArrayList<String> service_description,
                                   ArrayList<String> service_rate,
                                   ArrayList<String> service_type,
                                   ArrayList<String> service_image,
                                   ArrayList<String> service_status,
                                   ArrayList<String> service_postedDay,
                                   ArrayList<String> service_postedMonth,
                                   ArrayList<String> service_postedYear);
    }
    interface menu_homePresenter{
        //getting data
        void getServiceList(Context context);
    }
}
