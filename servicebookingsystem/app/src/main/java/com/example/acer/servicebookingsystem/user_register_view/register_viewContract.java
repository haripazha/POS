package com.example.acer.servicebookingsystem.user_register_view;

public interface register_viewContract {

    interface register_view{
        void toStepTwo();

    }
    interface register_presenter{
        void onSubmit(String name,
                      String contact_number,
                      String username,
                      String password,
                      String cpassword,
                      String homeBarangay,
                      String homeCity,
                      String homeAddress,
                      String userType);
    }
}
