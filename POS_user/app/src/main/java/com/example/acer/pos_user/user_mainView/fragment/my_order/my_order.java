package com.example.acer.pos_user.user_mainView.fragment.my_order;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_mainView.fragment.my_order.my_order_adapter.my_order_adapter;
import com.example.acer.pos_user.user_mainView.fragment.order.order;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_cart_adapter.order_shop_cart_adapter;
import com.example.acer.pos_user.user_mainView.fragment.order.order_shop_main.order_shop_main;
import com.example.acer.pos_user.user_mainView.user_mainView;

import java.util.ArrayList;

public class my_order extends Fragment implements my_orderContract.my_orderView{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    //mvp declaration
    my_orderPresenter presenter;

    //object declaration
    Button toOrder;

    //layout decaration
    LinearLayout no_order;
    LinearLayout my_order;

    //recyclerview declaration
    RecyclerView my_order_list;



    public my_order() {

    }

    public static my_order newInstance(String param1, String param2) {
        my_order fragment = new my_order();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        presenter = new my_orderPresenter(this);

        //object declaration
        toOrder = view.findViewById(R.id.to_order);
        no_order = view.findViewById(R.id.no_order);
        my_order = view.findViewById(R.id.my_order);
        my_order_list = view.findViewById(R.id.my_order_list);


        //run this method when fragment start
        systemStart();

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void populateMyOrderList(ArrayList<String> my_orderId,ArrayList<String> my_order_status,
                                    ArrayList<String> my_order_items,ArrayList<String> my_order_total) {

        Log.d("poplateMyOrder",""+my_orderId);


        if(my_orderId.size()>0){
            no_order.setVisibility(View.GONE);
            my_order.setVisibility(View.VISIBLE);


            //Populate item list data
            my_order_adapter adapter = new my_order_adapter(getContext());
            adapter.SetData(my_orderId,my_order_items,my_order_status,my_order_total);
            my_order_list.setAdapter(adapter);
            my_order_list.setLayoutManager(new GridLayoutManager(getContext(),1));

        }else{
            no_order.setVisibility(View.VISIBLE);
            my_order.setVisibility(View.GONE);
        }


    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void systemStart(){
        //to order

        toOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_mainView um = user_mainView.instance;
                if(um!=null){
                    um.my_orderProcessed();
                }
            }
        });

        //read my_order_status
        presenter.readMyOrders(getContext());
    }
}
