package com.example.acer.servicebookingsystem.provider_home_view.menu_service;

import android.content.Context;

import java.util.ArrayList;

public interface menu_serviceContract {
    interface menu_serviceVIew{
        void populateList(ArrayList<String> service_id,
                          ArrayList<String> service_company,
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
    interface menu_servicePresenter{
        void getLatestServices_list(Context context);
    }
}
