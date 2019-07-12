package com.example.acer.pos_kitchen_screen.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.pos_kitchen_screen.R;
import com.example.acer.pos_kitchen_screen.localhost;
import com.example.acer.pos_kitchen_screen.main.main;

import java.util.ArrayList;


public class main_adapter extends RecyclerView.Adapter<main_adapter.viewHolder> {
    private Context context;

    //List Declaration
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> order_date_1 = new ArrayList<>();
    ArrayList<String> order_items_1 = new ArrayList<>();



    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    localhost lc = new localhost();


    public main_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> my_orderID,ArrayList<String> order_date,ArrayList<String> order_items){

        this.id = my_orderID;
        this.order_date_1 = order_date;
        this.order_items_1 = order_items;


    }
    @NonNull
    @Override
    public main_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new main_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.main_adapter,null,false));


    }

    @Override
    public void onBindViewHolder(@NonNull main_adapter.viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();

        Log.d("order_ids",order_date_1.get(i));

        //objects
        viewHolder.order_id.setText("Order Ref: "+id.get(i));
        viewHolder.order_items.setText("Items: "+order_items_1.get(i));
        viewHolder.order_date.setText("Date: "+order_date_1.get(i));
        viewHolder.completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main m = main.instance;
                if(m!=null){
                    m.complete_orders(id.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return id!=null ? id.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        //Object declaration
        TextView order_id;
        TextView order_items;
        TextView order_date;
        Button completed;





        public viewHolder(@NonNull View itemView) {
            super(itemView);

            //object declaration
            order_id        = itemView.findViewById(R.id.order_id);
            order_items     = itemView.findViewById(R.id.order_items);
            order_date      = itemView.findViewById(R.id.order_date);
            completed       = itemView.findViewById(R.id.completed);
        }
    }
}
