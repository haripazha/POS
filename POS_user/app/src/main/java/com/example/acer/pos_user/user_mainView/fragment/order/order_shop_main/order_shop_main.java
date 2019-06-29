package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_checkout.order_shop_checkout;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shopCategories.order_shopCategories_dialog;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_cart_adapter.order_shop_cart_adapter;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_item_adapter.order_shop_item_adapter;

import java.util.ArrayList;

public class order_shop_main extends AppCompatActivity implements order_shop_mainContract.order_shopView{

    //mvp declaration
    order_shop_mainPresenter presenter;

    //object declaration
    ImageView home;
    ImageView categories;
    Button checkout;
    RecyclerView item_list;
    RecyclerView item_cart_list;

    //instance
    public static order_shop_main instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_shop_main);
        presenter = new order_shop_mainPresenter(this);
        instance = this;

        //object declaration
        home = findViewById(R.id.home);
        categories = findViewById(R.id.category);
        checkout = findViewById(R.id.checkout);
        item_list = findViewById(R.id.item_list);
        item_cart_list = findViewById(R.id.item_cart_list);

        //run this method
        systemStart();
    }

    public void systemStart(){

        //Go back to home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //Open categories dialog
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_shopCategories_dialog category = new order_shopCategories_dialog();
                category.show(getSupportFragmentManager(), "example dialog");
            }
        });

        //go to checkout
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(order_shop_main.this);
                pd.setMessage("Submitting your order...");
                pd.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.hide();

                        presenter.submitCustomerOrder();

                    }
                },1000);
            }
        });
    }


    //instance method for order shop categories dialog
    public void POS_categoryItem(String category_name){
        presenter.getItem(category_name);
    }

    //populate item list
    @Override
    public void populateItemList(ArrayList<String> item_id, ArrayList<String> category_name, ArrayList<String> item_name, ArrayList<String> item_price, ArrayList<String> item_desc, ArrayList<String> item_image, ArrayList<String> item_stock) {
        //Populate item list data
        order_shop_item_adapter adapter = new order_shop_item_adapter(getApplicationContext());
        adapter.SetData(item_id,item_name,item_price,item_desc,item_image,item_stock);
        item_list.setAdapter(adapter);
        item_list.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
    }

    @Override
    public void populateCartList(ArrayList<String> order_id, ArrayList<String> order_itemName, ArrayList<String> order_itemQuantity, ArrayList<String> order_customerUsername, ArrayList<String> order_itemPrice, ArrayList<String> order_itemTotal, ArrayList<String> order_itemDate, ArrayList<String> order_itemTime, ArrayList<String> order_status) {

        //Populate item list data
        order_shop_cart_adapter adapter = new order_shop_cart_adapter(getApplicationContext());
        adapter.SetData(order_id,order_itemName,order_itemQuantity,order_itemPrice,order_itemTotal,order_customerUsername);
        item_cart_list.setAdapter(adapter);
        item_cart_list.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

    }

    //delete from cart
    @Override
    public void proceedToDeletion(String custUsername, String itemName) {
        presenter.deleteFromCart(custUsername,itemName);
    }

    //insert item to customer_orders table
    public void order_item(String customerUsername,String item_name,
                           double item_quantity, double item_price,double item_total,
                           String item_time,String status){

        presenter.storeOrders_to_customerOrders(customerUsername,item_name,item_quantity,item_price,item_total,
                                item_time,status);
    }

    //confirm deletion of item from cart
    public void delete_from_cart(String customerUsername,String item_name){

        presenter.showDeleteConfirmation(customerUsername,item_name);
    }
}
