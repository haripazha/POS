package com.example.acer.servicebookingsystem.provider_home_view.menu_service.menu_service_adapter;

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
import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.localhost;

import java.util.ArrayList;

public class menu_service_adapter extends RecyclerView.Adapter<menu_service_adapter.viewHolder> {
    private Context context;

    //Object Declaration
    ArrayList<String> item_id_list_1;
    ArrayList<String> service_company_name  = new ArrayList<>();
    ArrayList<String> service_name          = new ArrayList<>();
    ArrayList<String> service_description   = new ArrayList<>();
    ArrayList<String> service_rate          = new ArrayList<>();
    ArrayList<String> service_type          = new ArrayList<>();
    ArrayList<String> service_image         = new ArrayList<>();
    ArrayList<String> service_status        = new ArrayList<>();
    ArrayList<String> service_postedDay     = new ArrayList<>();
    ArrayList<String> service_postedMonth   = new ArrayList<>();
    ArrayList<String> service_postedYear    = new ArrayList<>();


    //callback declaration
    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    localhost lc = new localhost();


    public menu_service_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> service_id,
                        ArrayList<String> service_company,
                        ArrayList<String> service_name,
                        ArrayList<String> service_description,
                        ArrayList<String> service_rate,
                        ArrayList<String> service_type,
                        ArrayList<String> service_image,
                        ArrayList<String> service_status,
                        ArrayList<String> service_postedDay,
                        ArrayList<String> service_postedMonth,
                        ArrayList<String> service_postedYear){

        this.item_id_list_1             = service_id;
        this.service_company_name       = service_company;
        this.service_name               = service_name;
        this.service_description        = service_description;
        this.service_rate               = service_rate;
        this.service_type               = service_type;
        this.service_image              = service_image;
        this.service_status             = service_status;
        this.service_postedDay          = service_postedDay;
        this.service_postedMonth        = service_postedMonth;
        this.service_postedYear         = service_postedYear;

        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.menu_service_adapter,null,false));



    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();

        viewHolder.service_title.setText(service_name.get(i));
        viewHolder.service_description.setText(service_description.get(i));

    }

    @Override
    public int getItemCount() {
        return item_id_list_1.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        //object declaration
        ImageView service_image;
        TextView service_title;
        TextView service_description;

        CardView card_container;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            service_image   = itemView.findViewById(R.id.service_image);
            service_title   = itemView.findViewById(R.id.service_title);
            service_description = itemView.findViewById(R.id.service_description);
            card_container      = itemView.findViewById(R.id.card);

        }
    }





}
