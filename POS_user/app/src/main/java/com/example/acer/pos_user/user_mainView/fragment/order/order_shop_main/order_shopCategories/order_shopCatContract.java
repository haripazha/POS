package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shopCategories;

import android.content.Context;

import java.util.ArrayList;

public interface order_shopCatContract {
    interface order_shopCatView{
        void populateCategoryList(ArrayList<String> category_name);
    }
    interface order_shopCatPresenter{
        void getCategories(Context context);

    }

}
