package com.example.acer.jm_pos.reports.summary_sales.generate_reportSales;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.inventory.inventory_view_items.inventory_view_adapter;
import com.example.acer.jm_pos.inventory.inventory_view_items.inventory_view_items;
import com.example.acer.jm_pos.localhost;

import java.util.ArrayList;

public class generate_report_adapter extends RecyclerView.Adapter<generate_report_adapter.viewHolder> {
    private Context context;

    //Object Declaration
    ArrayList<String> item_id_list_1;


    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    com.example.acer.jm_pos.localhost lc = new localhost();


    public generate_report_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> item_id_list){

        this.item_id_list_1        = item_id_list;

        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public generate_report_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new generate_report_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.inventory_viewadapter,null,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final generate_report_adapter.viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();


    }

    @Override
    public int getItemCount() {
        return item_id_list_1.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        public viewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }





}
