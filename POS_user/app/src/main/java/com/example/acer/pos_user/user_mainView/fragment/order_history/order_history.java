package com.example.acer.pos_user.user_mainView.fragment.order_history;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.acer.pos_user.R;

public class order_history extends Fragment implements order_historyContract.orderHistory_view{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //mvp declaration
    order_historyPresenter presenter;

    //object declaration
    EditText start_date;
    EditText end_date;

    public order_history() {

    }

    public static order_history newInstance(String param1, String param2) {
        order_history fragment = new order_history();
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

        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        presenter = new order_historyPresenter(this);

        //object declaration
        start_date = view.findViewById(R.id.start_date);
        end_date   = view.findViewById(R.id.end_date);

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
    public void populateStartDate(String month, String day, String year) {
        //populate the edittext of date
        start_date.setText(month+"/"+day+"/"+year);


        //stored the date start date details
        presenter.storedFilteredStartDate(month,day,year);
    }

    @Override
    public void populateEndDate(String month, String day, String year) {

        //populate the edittext of date
        end_date.setText(month+"/"+day+"/"+year);

        //stored the date end date details
        presenter.storedFilteredEndDate(month,day,year);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void systemStart(){
        //show start date dialog picker
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showStartDateDialog(getContext());
            }
        });

        //show start date dialog picker
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showEndDateDialog(getContext());
            }
        });
    }
}

