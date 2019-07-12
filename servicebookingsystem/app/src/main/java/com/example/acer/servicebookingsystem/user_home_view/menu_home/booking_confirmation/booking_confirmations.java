package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_confirmation;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.servicebookingsystem.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class booking_confirmations extends FragmentActivity implements OnMapReadyCallback,booking_confirmationContract.booking_view {
    //mvp declaration
    booking_confirmationPresenter presenter;

    private GoogleMap mMap;

    //Object declaration
    ImageView back_button;
    TextView service_title;
    TextView service_description;
    TextView service_address;
    TextView service_distance;
    TextView service_rate;
    Button book_now;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmations);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter = new booking_confirmationPresenter(this);


        //object declaration
        back_button     = findViewById(R.id.back_button);
        service_title   = findViewById(R.id.service_title);
        service_description = findViewById(R.id.service_description);
        service_address     = findViewById(R.id.service_address);
        service_distance    = findViewById(R.id.service_distance);
        service_rate        = findViewById(R.id.service_rate);
        book_now            = findViewById(R.id.book_now);


        //run this method when systemStart;
        systemStart();
    }

    //run this method when systemStart
    public void systemStart(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //get the booking services details
        presenter.get_booking_details();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
