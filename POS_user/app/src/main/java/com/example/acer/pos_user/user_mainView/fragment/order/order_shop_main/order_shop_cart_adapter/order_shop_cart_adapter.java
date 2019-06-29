package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_cart_adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.pos_user.R;
import com.example.acer.pos_user.localhost;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_item_adapter.order_shop_item_adapter;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class order_shop_cart_adapter extends RecyclerView.Adapter<order_shop_cart_adapter.viewHolder> {
    private Context context;

    //List Declaration
    ArrayList<String> id            = new ArrayList<>();
    ArrayList<String> item_name     = new ArrayList<>();
    ArrayList<String> item_quantity = new ArrayList<>();
    ArrayList<String> item_price    = new ArrayList<>();
    ArrayList<String> item_total    = new ArrayList<>();
    ArrayList<String> stock         = new ArrayList<>();
    ArrayList<String> custUsername  = new ArrayList<>();

    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    com.example.acer.pos_user.localhost lc = new localhost();


    public order_shop_cart_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> order_id,ArrayList<String> item_name_1,ArrayList<String> item_quantity_1,ArrayList<String> item_price_1,ArrayList<String>
                        item_total_1,ArrayList<String> custUsername){
        this.id = order_id;
        this.item_name = item_name_1;
        this.item_quantity = item_quantity_1;
        this.item_price = item_price_1;
        this.item_total = item_total_1;
        this.custUsername = custUsername;

        Log.d("order_itemName",item_name.toString());


    }
    @NonNull
    @Override
    public order_shop_cart_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new order_shop_cart_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.order_shop_cart_adapter,null,false));


    }

    @Override
    public void onBindViewHolder(@NonNull order_shop_cart_adapter.viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();

        viewHolder.item_name_1.setText(item_name.get(i));
        viewHolder.item_total_1.setText(item_quantity.get(i)+" X "+item_price.get(i));
        viewHolder.item_stock_1.setText(item_total.get(i));

        //when delete item press
        viewHolder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeletion(item_name.get(i),custUsername.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return id!=null ? id.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        //Object declaration
        TextView item_name_1;
        TextView item_total_1;
        TextView item_stock_1;
        ImageView delete_item;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            //object declaration
            item_name_1 = itemView.findViewById(R.id.item_name);
            item_total_1 =itemView.findViewById(R.id.item_price);
            item_stock_1 = itemView.findViewById(R.id.item_total);
            delete_item = itemView.findViewById(R.id.delete_item);

        }
    }

    //confirm deletion
    public void confirmDeletion(final String item_name, final String custUsername){

            order_shop_main om = order_shop_main.instance;
            if(om!=null){
                om.delete_from_cart(custUsername,item_name);
            }
    }

}
