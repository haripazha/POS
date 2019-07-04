package com.example.acer.jm_pos.orders.view_completed_order;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.localhost;
import com.example.acer.jm_pos.orders.view_order.view_order;
import com.example.acer.jm_pos.orders.view_order.view_order_adapter;

import java.util.ArrayList;

public class view_completed_adapter extends RecyclerView.Adapter<view_completed_adapter.viewHolder> {
    private Context context;

    //Object Declaration
    ArrayList<String> item_id_list_1;
    ArrayList<String> my_order_status_1 = new ArrayList<>();
    ArrayList<String> my_order_customUsername = new ArrayList<>();
    ArrayList<String> my_order_items = new ArrayList<>();
    ArrayList<String> my_order_total = new ArrayList<>();


    //callback declaration
    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    com.example.acer.jm_pos.localhost lc = new localhost();


    public view_completed_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> item_id_list,
                        ArrayList<String> my_order_status,
                        ArrayList<String> my_order_customUsername,
                        ArrayList<String> my_order_items,
                        ArrayList<String> my_order_total){

        this.item_id_list_1             = item_id_list;
        this.my_order_status_1          = my_order_status;
        this.my_order_customUsername    = my_order_customUsername;
        this.my_order_items             = my_order_items;
        this.my_order_total             = my_order_total;
    }
    @NonNull
    @Override
    public view_completed_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new view_completed_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.view_order_adapter,null,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final view_completed_adapter.viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();

        //objects
        viewHolder.order_id.setText("order ref:000000"+item_id_list_1.get(i));
        viewHolder.date.setText("Date: ");
        viewHolder.status.setText("Status: "+my_order_status_1.get(i));
        viewHolder.items.setText("Items: "+my_order_items.get(i));
        viewHolder.order_total.setText("Total: Php "+my_order_total.get(i));

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Storing user_id to local
                SharedPreferences store_user_id = context.getSharedPreferences("order_details_id", Context.MODE_PRIVATE);
                SharedPreferences.Editor store_username_editor = store_user_id.edit();
                store_username_editor.putString("order_details_id",item_id_list_1.get(i));
                store_username_editor.commit();

                view_order vo = view_order.instance;
                if(vo!=null){
                    vo.go_to_View_order();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return item_id_list_1.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        //Object declaration

        TextView order_id;
        TextView date;
        TextView status;
        TextView items;
        TextView order_total;

        ImageView order_statusImage;
        LinearLayout container;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            //object declaration
            order_id    = itemView.findViewById(R.id.order_id);
            date        = itemView.findViewById(R.id.date);
            status      = itemView.findViewById(R.id.status);
            items       = itemView.findViewById(R.id.items);
            order_total = itemView.findViewById(R.id.order_total);
            container   = itemView.findViewById(R.id.container);
        }
    }


}
