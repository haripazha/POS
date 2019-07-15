package com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_confirmation;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.booking_details.booking_details;
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
    TextView service_title_1;
    TextView service_description_1;
    TextView service_address_1;
    TextView service_distance_1;
    TextView service_rate_1;
    Button book_now;

    //variable declaration
    double latitudes = 0;
    double longitudes = 0;


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
        back_button             = findViewById(R.id.back_button);
        service_title_1         = findViewById(R.id.service_title);
        service_description_1   = findViewById(R.id.service_description);
        service_address_1       = findViewById(R.id.service_address);
        service_distance_1      = findViewById(R.id.service_distance);
        service_rate_1          = findViewById(R.id.service_rate);
        book_now                = findViewById(R.id.book_now);




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
                Intent intent = new Intent(booking_confirmations.this,booking_details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        //get the booking services details
        presenter.get_booking_details();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("coordinates",latitudes +","+longitudes);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitudes, longitudes);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void populateBookingDetails(String service_id, String service_company, String service_name, String service_description, String service_rate, String service_type, String service_image, String service_status, String service_postedDay, String service_postedMonth, String service_postedYear, String company_latitude, String company_longitude, String company_homeAddress, String company_homeBarangay, String company_homeCity,
                                       String distance) {
        service_title_1.setText(service_name);
        service_description_1.setText(service_description);
        service_address_1.setText("Location: "+company_homeAddress+" "+company_homeBarangay+" "+company_homeCity);
        service_rate_1.setText("Rate: Php "+service_rate);
        latitudes = Double.parseDouble(company_latitude);
        longitudes = Double.parseDouble(company_longitude);
        service_distance_1.setText("Distance: "+String.format("%.2f",Double.parseDouble(distance))+ " km away");
        Log.d("lattss",""+latitudes);



        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitudes, longitudes);
        mMap.addMarker(new MarkerOptions().position(latLng).title(service_name+" Location"));
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

    }
}
