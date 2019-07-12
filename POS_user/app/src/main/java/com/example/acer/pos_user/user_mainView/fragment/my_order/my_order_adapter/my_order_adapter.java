package com.example.acer.pos_user.user_mainView.fragment.my_order.my_order_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.localhost;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_cart_adapter.order_shop_cart_adapter;

import java.util.ArrayList;

public class my_order_adapter extends RecyclerView.Adapter<my_order_adapter.viewHolder> {
    private Context context;

    //List Declaration
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> order_items = new ArrayList<>();
    ArrayList<String> order_status = new ArrayList<>();
    ArrayList<String> order_order_total = new ArrayList<>();





    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    com.example.acer.pos_user.localhost lc = new localhost();


    public my_order_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> my_orderID,ArrayList<String> order_items_1,
                        ArrayList<String> order_status_1,ArrayList<String> order_total_1){

        this.id = my_orderID;
        this.order_items = order_items_1;
        this.order_status = order_status_1;
        this.order_order_total = order_total_1;


    }
    @NonNull
    @Override
    public my_order_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new my_order_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.my_order_adapter,null,false));


    }

    @Override
    public void onBindViewHolder(@NonNull my_order_adapter.viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();

        //objects
        viewHolder.order_id.setText("order ref:000000"+id.get(i));
        viewHolder.date.setText("Date: ");
        viewHolder.status.setText("Status: "+order_status.get(i));
        viewHolder.items.setText("Items: "+order_items.get(i));
        viewHolder.order_total.setText("Total: Php "+order_order_total.get(i));

        if(order_status.get(i).equals("Process")){

        }else if(order_status.get(i).equals("Completed")){

        }
    }

    @Override
    public int getItemCount() {
        return id!=null ? id.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        //Object declaration

        TextView order_id;
        TextView date;
        TextView status;
        TextView items;
        TextView order_total;

        ImageView order_statusImage;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            //object declaration
            order_id    = itemView.findViewById(R.id.order_id);
            date        = itemView.findViewById(R.id.date);
            status      = itemView.findViewById(R.id.status);
            items       = itemView.findViewById(R.id.items);
            order_total = itemView.findViewById(R.id.order_total);
        }
    }
}
