package com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shopCategories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_main;

import java.util.ArrayList;

public class order_shopCategories_dialog extends AppCompatDialogFragment implements order_shopCatContract.order_shopCatView{
    //mvp declaration
    order_shopCatPresenter presenter;

    //object declartion
    RecyclerView cat_list;

    //instance
    public static order_shopCategories_dialog instance;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.order_shopcategories_dialog, null);
        presenter = new order_shopCatPresenter(this);
        instance = this;

        //object declaration
        cat_list = view.findViewById(R.id.cat_list);

        builder.setView(view)
                .setTitle("Categories")
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        //System start
        systemStart();

        return builder.create();
    }


    //run this method when dialog opened
    public void systemStart(){
        presenter.getCategories(getContext());
    }

    @Override
    public void populateCategoryList(ArrayList<String> category_name) {
        Log.d("categoryList",category_name.toString());
        POS_subCategoryDialog_Adapter adapter = new POS_subCategoryDialog_Adapter(getActivity());
        adapter.SetData(category_name);
        cat_list.setAdapter(adapter);
        cat_list.setLayoutManager(new GridLayoutManager(getContext(),4));
    }

    //dismiss this data
    public void dismiss(String category_name){
        dismiss();
        order_shop_main pm = order_shop_main.instance;

        if(pm!=null){
            pm.POS_categoryItem(category_name);

        }
    }
}
