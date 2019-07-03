package com.example.acer.pos_user.user_mainView.fragment.order_history.generated_history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.localhost;
import com.example.acer.pos_user.user_mainView.fragment.my_order.my_order_adapter.my_order_adapter;

import java.util.ArrayList;

public class generated_history_adapter extends RecyclerView.Adapter<generated_history_adapter.viewHolder> {
    private Context context;

    //List Declaration
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> my_order_status_1    = new ArrayList<>();
    ArrayList<String> my_order_customer_1   = new ArrayList<>();
    ArrayList<String> my_order_items_1      = new ArrayList<>();
    ArrayList<String> my_order_total_1      = new ArrayList<>();
    ArrayList<String> my_order_day_1        = new ArrayList<>();
    ArrayList<String> my_order_month_1      = new ArrayList<>();
    ArrayList<String> my_order_year_1       = new ArrayList<>();

    //network address Declaration
    String localhost = "";
    String image_url = localhost + "/MEATSHOP_POS_SALE/image_upload/";
    com.example.acer.pos_user.localhost lc = new localhost();


    public generated_history_adapter(Context context){
        this.context = context;
    }
    public void SetData(ArrayList<String> my_orderID,ArrayList<String> my_order_status,
                        ArrayList<String> my_order_customUsername,ArrayList<String> my_order_items,
                        ArrayList<String> my_order_total,ArrayList<String> my_order_day,
                        ArrayList<String> my_order_month,ArrayList<String> my_order_year){

        this.id                     = my_orderID;
        this.my_order_status_1      = my_order_status;
        this.my_order_customer_1    = my_order_customUsername;
        this.my_order_items_1       = my_order_items;
        this.my_order_total_1       = my_order_total;
        this.my_order_day_1         = my_order_day;
        this.my_order_month_1       = my_order_month;
        this.my_order_year_1        = my_order_year;

    }
    @NonNull
    @Override
    public generated_history_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new generated_history_adapter.viewHolder(LayoutInflater.from(context).inflate(R.layout.generated_history_adapter,null,false));


    }

    @Override
    public void onBindViewHolder(@NonNull generated_history_adapter.viewHolder viewHolder, final int i) {
        localhost = lc.getLocalhost();

        //object declaration
        viewHolder.order_ref.setText("Ref: 000000"+id.get(i));

        //if status is archived
        if(my_order_status_1.get(i).equals("archived")){
            viewHolder.order_status.setText("Status: Completed");
        }else{
            viewHolder.order_status.setText("Status: "+my_order_status_1.get(i));
        }

        //set Date
        viewHolder.order_date.setText("Date Processed: "+my_order_month_1.get(i)+" "+my_order_day_1.get(i)+", "+my_order_year_1.get(i));

        //go to order_details
        viewHolder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generated_history gh = generated_history.instance;
                if(gh!=null){
                    gh.storeOrder_ID(id.get(i));
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
        TextView order_ref;
        TextView order_status;
        TextView order_date;
        Button view_details;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            order_ref       = itemView.findViewById(R.id.order_ref);
            order_status    = itemView.findViewById(R.id.order_status);
            order_date      = itemView.findViewById(R.id.date_processed);
            view_details    = itemView.findViewById(R.id.view_details);

        }
    }
}
