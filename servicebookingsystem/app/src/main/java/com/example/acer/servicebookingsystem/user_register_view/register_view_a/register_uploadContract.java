package com.example.acer.servicebookingsystem.user_register_view.register_view_a;

public interface register_uploadContract {
    interface register_uploadView{
        void showValidId_container(String userType);
        void to_StepThree();

    }
    interface register_uploadPresenter{
            void onSubmit(String image_string,
                          String email,
                          String valid_id_img_string,
                          String latitude,
                          String longtitude,
                          String company_numberOf_employee,
                          String company_address,
                          String company_barangay,
                          String company_service,
                          String company_city,
                          String company_name,
                          String company_description);

            void validateUserType();

            void enableGPS();
    }
}
