package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pos_user.localhost;
import com.example.acer.pos_user.user_mainView.fragment.order.order;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shopCategories.order_shopCategories_dialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class order_shop_mainPresenter implements order_shop_mainContract.order_shopPresenter {
    //mvp declaration
    order_shop_mainContract.order_shopView mView;
    Context context;

    //network address Declaration
    String localhost = "";
    String main_subCat = localhost + "/MEATSHOP_POS_SALE/android_php/POS_main/POS_subCategory/pos_subCategory.php";
    String user_cart = localhost + "/MEATSHOP_POS_SALE/android_php/POS_customer/POS_order/pos_order.php";
    localhost lc = new localhost();

    order_shop_mainPresenter(order_shop_mainContract.order_shopView view){
        this.mView = view;
        this.context = (Context) mView;


    }


    @Override
    public void getItem(final String category_name_a) {
        localhost = lc.getLocalhost();

        //Object declaration
        final ArrayList<String> item_id = new ArrayList<>();
        final ArrayList<String> category_name = new ArrayList<>();
        final ArrayList<String> item_name = new ArrayList<>();
        final ArrayList<String> item_price = new ArrayList<>();
        final ArrayList<String> item_desc = new ArrayList<>();
        final ArrayList<String> item_image = new ArrayList<>();
        final ArrayList<String> item_stock = new ArrayList<>();


        //request
        StringRequest getItem = new StringRequest(Request.Method.POST, localhost + main_subCat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getItemResponse",response);
                if(!response.contains("failed")){
                    if(!response.contains("no_item")){
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            JSONArray jsonArray = jsonObject.getJSONArray("item_result");
                            for(int i = 0;i < jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                //ArrayList item desc
                                item_id.add(jsonObject1.getString("item_id"));
                                category_name.add(jsonObject1.getString("category_name"));
                                item_name.add(jsonObject1.getString("item_name"));
                                item_price.add(jsonObject1.getString("item_price"));
                                item_desc.add(jsonObject1.getString("item_desc"));
                                item_image.add(jsonObject1.getString("item_image"));
                                item_stock.add(jsonObject1.getString("item_stock"));

                            }

                            mView.populateItemList(item_id,category_name,item_name,item_price,item_desc,item_image,item_stock);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(context,"No Item",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"Failed to retrieved data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getItemError",error.toString());
                Toast.makeText(context,"Connection Problem",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getItem");
                hashMap.put("cat_getItem",category_name_a);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(getItem);
    }

    //store to customer orders
    @Override
    public void storeOrders_to_customerOrders(final String customerUsername, final String item_name, final double item_quantity, final double item_price, double item_total, String item_time, String status) {
        localhost = lc.getLocalhost();

        StringRequest insertTo_customerOrder = new StringRequest(Request.Method.POST, localhost + user_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("insertCustRes",response);
                if(!response.contains("failed")){
                    if(response.contains("success")){
                        getCartList(customerUsername);
                    }
                }else{
                    Toast.makeText(context,"Failed to process data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("insertCustError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","insert_custOrders");
                hashMap.put("insert_itemName",item_name);
                hashMap.put("insert_itemQuantity",String.valueOf(item_quantity));
                hashMap.put("insert_customerUsername",customerUsername);
                hashMap.put("insert_itemPrice",String.valueOf(item_price));
                hashMap.put("status","not ready");


                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(insertTo_customerOrder);
    }

    @Override
    public void deleteFromCart(final String cust_name, final String item_name) {
        localhost = lc.getLocalhost();

        StringRequest deleteFromCart = new StringRequest(Request.Method.POST, localhost + user_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("deleteFromCart",response.toString());
                if(!response.contains("failed")){
                    Toast.makeText(context,"Item has been deleted!",Toast.LENGTH_LONG).show();
                    getCartList(cust_name);
                }else{
                    Toast.makeText(context,"Failed to prcoess the query!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("deleteFromCartErr",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","delete_fromCart");
                hashMap.put("order_delUsername",cust_name);
                hashMap.put("order_delItemName",item_name);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(deleteFromCart);
    }

    @Override
    public void showDeleteConfirmation(final String username, final String item_name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Prompt");
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pd = new ProgressDialog(context);
                        pd.setMessage("Please Wait");
                        pd.show();
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                pd.hide();
                                mView.proceedToDeletion(username,item_name);
                            }
                        }, 2000);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void submitCustomerOrder() {
        //Object declaration
        localhost = lc.getLocalhost();

        //get the value of username
        SharedPreferences get_username = context.getSharedPreferences("customer_username", Context.MODE_PRIVATE);
        final String username = get_username.getString("customer_username","");


        StringRequest submit_orderUsername = new StringRequest(Request.Method.POST, localhost + user_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("submitOr_res",response.toString());
                if(!response.contains("failed")){
                    if(response.contains("success")){
                        Toast.makeText(context,"Your order has been submitted!",Toast.LENGTH_LONG).show();
                        getCartList(username);
                    }
                }else{
                    Toast.makeText(context,"Failed to process your request!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("submitOr_error",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","submit_customerorders");
                hashMap.put("submit_orderUsername",username);

                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(submit_orderUsername);

    }

    //getCartList
    public void getCartList(final String customerUsername){
        localhost = lc.getLocalhost();

        final ArrayList<String> order_id = new ArrayList<>();
        final ArrayList<String> order_itemName = new ArrayList<>();
        final ArrayList<String> order_itemQuantity = new ArrayList<>();
        final ArrayList<String> order_customerUsername = new ArrayList<>();
        final ArrayList<String> order_itemPrice = new ArrayList<>();
        final ArrayList<String> order_itemTotal = new ArrayList<>();
        final ArrayList<String> order_itemDate = new ArrayList<>();
        final ArrayList<String> order_itemTime = new ArrayList<>();
        final ArrayList<String> order_status  = new ArrayList<>();


        StringRequest getCartList = new StringRequest(Request.Method.POST, localhost + user_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("getCartListRes",response.toString());
                if(!response.contains("failed")){
                    try {
                        JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        JSONArray jsonArray = jsonObject.getJSONArray("Cust_Orders");
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //Getting the value and storing to arraylist
                            order_id.add(jsonObject1.getString("order_id"));
                            order_itemName.add(jsonObject1.getString("order_itemName"));
                            order_itemQuantity.add(jsonObject1.getString("order_itemQuantity"));
                            order_customerUsername.add(jsonObject1.getString("order_customerUsername"));
                            order_itemPrice.add(jsonObject1.getString("order_itemPrice"));
                            order_itemTotal.add(jsonObject1.getString("order_itemTotal"));
                            order_itemDate.add(jsonObject1.getString("order_itemDate"));
                            order_itemTime.add(jsonObject1.getString("order_itemTime"));
                            order_status.add(jsonObject1.getString("order_status"));

                            //Populate cart list of the view
                            mView.populateCartList(order_id,order_itemName,order_itemQuantity,order_customerUsername,order_itemPrice,
                                    order_itemTotal,order_itemDate,order_itemTime,order_status);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("getCartPrintStact",e.toString());
                        Toast.makeText(context,"Exception Error!",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(context,"Failed to retrieve data!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getCartListError",error.toString());
                Toast.makeText(context,"Connection Problem!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("function","getCustomerCartList");
                hashMap.put("get_CustCartUsername",customerUsername);

                return hashMap;

            }
        };
        Volley.newRequestQueue(context).add(getCartList);
    }
}
