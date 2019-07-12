package com.example.acer.servicebookingsystem.provider_home_view.menu_service.button_add_service;

public interface add_serviceContract {

    interface add_serviceView{
        void onInsertSuccess();
        void onInsertFailed();
    }
    interface add_servicePresenter{
        void add_service(String service_name,
                         String service_description,
                         String service_rate,
                         String service_img_imgString,
                         String service_type);
    }
}
