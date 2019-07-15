package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_confirmation;

public interface booking_confirmationContract {
    interface booking_view{

        //fill up booking confirmation details
        void populateBookingDetails(
                String service_id,
                String service_company,
                String service_name,
                String service_description,
                String service_rate,
                String service_type,
                String service_image,
                String service_status,
                String service_postedDay,
                String service_postedMonth,
                String service_postedYear,
                String company_latitude,
                String company_longitude,
                String company_homeAddress,
                String company_homeBarangay,
                String company_homeCity,
                String distance
        );
    }
    interface booking_presenter{
        //getting the booking details
        void get_booking_details();
    }
}
