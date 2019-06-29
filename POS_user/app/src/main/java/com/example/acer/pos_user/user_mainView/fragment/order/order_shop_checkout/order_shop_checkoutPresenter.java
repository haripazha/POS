package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_checkout;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pos_user.localhost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class order_shop_checkoutPresenter implements order_shop_checkoutContract.order_shopCheckPresenter {

    //mvp declaration
    order_shop_checkoutContract.order_shopCheckView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String user_cart = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_order/pos_order.php";
    com.example.acer.pos_user.localhost lc = new localhost();


    //object declaration
    int item_count = 0;
    double item_total = 0;


    order_shop_checkoutPresenter(order_shop_checkoutContract.order_shopCheckView view){
        this.mView = view;
        this.context = (Context) mView;

    }


    //submit orders
    @Override
    public void submitOrders() {

    }
}
