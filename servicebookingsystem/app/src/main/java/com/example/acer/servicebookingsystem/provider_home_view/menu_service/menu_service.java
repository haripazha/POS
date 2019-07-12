package com.example.acer.servicebookingsystem.provider_home_view.menu_service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.provider_home_view.menu_service.button_add_service.add_service;
import com.example.acer.servicebookingsystem.provider_home_view.menu_service.menu_service_adapter.menu_service_adapter;

import java.util.ArrayList;

public class menu_service extends Fragment implements menu_serviceContract.menu_serviceVIew{
    //mvp declaratin
    menu_servicePresenter presenter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //object declaration
    Button add_services;

    RecyclerView my_service_list;


    public menu_service() {
        // Required empty public constructor
    }


    public static menu_service newInstance(String param1, String param2) {
        menu_service fragment = new menu_service();
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

        View view = inflater.inflate(R.layout.fragment_menu_service, container, false);
        presenter = new menu_servicePresenter(this);

        //object declaration
        add_services = view.findViewById(R.id.add_service);
        my_service_list = view.findViewById(R.id.my_service_list);



        //run this method when system start
        systemStart();

        return view;
    }
    public void systemStart(){

        //go to service
        add_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),add_service.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        //get latest update of services
        presenter.getLatestServices_list(getContext());
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
    public void populateList(ArrayList<String> service_id,
                             ArrayList<String> service_company,
                             ArrayList<String> service_name,
                             ArrayList<String> service_description,
                             ArrayList<String> service_rate,
                             ArrayList<String> service_type,
                             ArrayList<String> service_image,
                             ArrayList<String> service_status,
                             ArrayList<String> service_postedDay,
                             ArrayList<String> service_postedMonth,
                             ArrayList<String> service_postedYear) {


        menu_service_adapter adapter = new menu_service_adapter(getContext());
        adapter.SetData(service_id,service_company,service_name,service_description,
                service_rate,service_type,service_image,service_status,service_postedDay,service_postedMonth,service_postedYear);
        my_service_list.setAdapter(adapter);
        my_service_list.setLayoutManager(new GridLayoutManager(getContext(),1));
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
