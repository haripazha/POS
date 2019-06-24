package com.example.acer.pos_user.user_register;

public interface user_registerContract {
    interface userReg_view{
        void toUsername_password_data();

    }

    interface userReg_presenter{
        void storedCustomer_Details(String name,String address, String contact_number);
    }
}
