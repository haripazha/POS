package com.example.acer.jm_pos.reports.item_inventory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class item_inventoryPresenter implements item_inventoryContract.item_inventoryPresenter {

    //mvp declaration
    item_inventoryContract.item_inventoryView mView;
    Context context;

    item_inventoryPresenter(item_inventoryContract.item_inventoryView view){
        this.mView = view;
        this.context = (Context) mView;

    }

    //generate dialog
    @Override
    public void startAndEnd_validationAlert() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Prompt");
        builder1.setMessage("Please select Start and End Date!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void generate_inventoryBalance() {

    }
}
