package com.example.acer.servicebookingsystem.user_home_view.menu_home.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.menu_home;

import java.util.ArrayList;

public class menu_home_adapter extends RecyclerView.Adapter<menu_home_adapter.viewHolder> {
    private Context context;

    ArrayList<String> id                    = new ArrayList<>();
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


    public menu_home_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> service_id,
                        ArrayList<String> service_company_name_1,
                        ArrayList<String> service_name_1,
                        ArrayList<String> service_description_1,
                        ArrayList<String> service_rate_1,
                        ArrayList<String> service_type_1,
                        ArrayList<String> service_image_1,
                        ArrayList<String> service_status_1,
                        ArrayList<String> service_postedDay_1,
                        ArrayList<String> service_postedMonth_1,
                        ArrayList<String> service_postedYear_1){

        this.id = service_id;
        this.service_company_name   = service_company_name_1;
        this.service_name           = service_name_1;
        this.service_description    = service_description_1;
        this.service_rate           = service_rate_1;
        this.service_type           = service_type_1;
        this.service_image          = service_image_1;
        this.service_status         = service_status_1;
        this.service_postedDay      = service_postedDay_1;
        this.service_postedMonth    = service_postedMonth_1;
        this.service_postedYear     = service_postedYear_1;


    }
    @NonNull
    @Override
    public menu_home_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new menu_home_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.menu_home_adapter,null,false));


    }

    @Override
    public void onBindViewHolder(@NonNull menu_home_adapter.viewHolder viewHolder, final int i) {

        viewHolder.service_title.setText(service_name.get(i));
        //viewHolder.service_description.setText(service_description.get(i));
        viewHolder.service_rate.setText("Php "+service_rate.get(i));
        viewHolder.service_type.setText(service_type.get(i));

        viewHolder.card_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_home mh = menu_home.instance;
                if(mh!=null){
                    //Storing user_id to local
                    SharedPreferences store_user_id = context.getSharedPreferences("booking_service_id", Context.MODE_PRIVATE);
                    SharedPreferences.Editor store_username_editor = store_user_id.edit();
                    store_username_editor.putString("booking_id",id.get(i));
                    store_username_editor.commit();

                    mh.goToBooking_confirmation();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return id!=null ? id.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{


        //object declaration
        ImageView service_image;
        TextView service_title;
        TextView service_type;
        TextView service_rate;

        CardView card_container;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            service_image   = itemView.findViewById(R.id.service_image);
            service_title   = itemView.findViewById(R.id.service_title);
            //service_description = itemView.findViewById(R.id.service_description);
            card_container      = itemView.findViewById(R.id.card);
            service_rate        = itemView.findViewById(R.id.service_rate);
            service_type        = itemView.findViewById(R.id.service_type);
        }
    }
}
