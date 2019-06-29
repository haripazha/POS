package com.example.acer.jm_pos.reports;

import android.content.Context;
import android.content.Intent;

import com.example.acer.jm_pos.reports.item_inventory.generate_inventoryBalance.generate_inventoryBalance;
import com.example.acer.jm_pos.reports.item_inventory.item_inventory;
import com.example.acer.jm_pos.reports.summary_sales.summary_sales;
import com.example.acer.jm_pos.reports.top_products.top_products;

public class report_presenter implements report_contract.report_presenter {

    //MVP declaration
    report_contract.report_view mView;




    report_presenter(report_contract.report_view view){
        this.mView = view;
    }

    @Override
    public void top_products(Context context) {
        Intent intent = new Intent(context,top_products.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    public void to_salesAndProfitsReport(Context context) {
        Intent intent = new Intent(context,summary_sales.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);

    }

    @Override
    public void to_itemInventory(Context context) {
       /* Intent intent = new Intent(context,item_inventory.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);*/

        Intent intent = new Intent(context,generate_inventoryBalance.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }
}
