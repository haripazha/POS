package com.example.acer.servicebookingsystem.user_home_view.menu_home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.adapter.menu_home_adapter;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_confirmation.booking_confirmations;

import java.util.ArrayList;


public class menu_home extends Fragment implements menu_homeContract.menu_homeView{
    //mvp declaration
    menu_homePresenter presenter;
    public static menu_home instance;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //object declaration
    RecyclerView service_list;



    public menu_home() {

    }

    public static menu_home newInstance(String param1, String param2) {
        menu_home fragment = new menu_home();
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

        View view = inflater.inflate(R.layout.fragment_menu_home, container, false);
        presenter = new menu_homePresenter(this);
        instance = this;

        //object declaration

        service_list = view.findViewById(R.id.service_list);


        //run this method when system start
        systemStart();
        
        return view;
    }

    //run this method system start
    public void systemStart(){
        presenter.getServiceList(getContext());
    }

    //function for home adapter
    public void goToBooking_confirmation(){

        Intent intent = new Intent(getContext(),booking_confirmations.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
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
    public void populateRecycler_view(ArrayList<String> service_id,
                                      ArrayList<String> service_company_name,
                                      ArrayList<String> service_name,
                                      ArrayList<String> service_description,
                                      ArrayList<String> service_rate,
                                      ArrayList<String> service_type,
                                      ArrayList<String> service_image,
                                      ArrayList<String> service_status,
                                      ArrayList<String> service_postedDay,
                                      ArrayList<String> service_postedMonth,
                                      ArrayList<String> service_postedYear) {

        Log.d("service_nmes",service_name.toString());

        menu_home_adapter adapter = new menu_home_adapter(getContext());
        adapter.SetData(service_id,
                service_company_name,service_name,service_description,service_rate,service_type,service_image,service_status,service_postedDay,service_postedMonth,service_postedYear);
        service_list.setAdapter(adapter);
        service_list.setLayoutManager(new GridLayoutManager(getContext(),2));

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
