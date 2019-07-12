package com.example.acer.servicebookingsystem.user_register_view.register_view_a;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_register_view.register_view_b.register_view_b;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class register_upload_photos extends AppCompatActivity implements register_uploadContract.register_uploadView {

    //mvp declaration
    register_uploadPresenter presenter;

    //object declaration
    CardView valid_id_container;
    CardView company_details_container;


    ImageView back_button;
    ImageView user_profile;
    ImageView user_valid_id;

    TextInputLayout email;
    TextInputLayout company_description;
    TextInputLayout company_number_ofEmployee;
    TextInputLayout company_address;
    TextInputLayout company_barangay;
    TextInputLayout company_name;



    Spinner company_service;
    Spinner company_city;


    Button submit;

    String user_profile_img_image_string = "";
    String valid_id_image_string = "";

    //Request code declaration
    private static  final int REQUEST_LOCATION=1;

    //location declaration
    LocationManager locationManager;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_upload_photos);
        presenter = new register_uploadPresenter(this);

        //object declaration
        back_button         = findViewById(R.id.back_button);
        user_profile        = findViewById(R.id.user_profile_pic);
        email               = findViewById(R.id.email);
        submit              = findViewById(R.id.submit);
        valid_id_container  = findViewById(R.id.valid_id);
        user_valid_id       = findViewById(R.id.user_valid_id);
        company_details_container = findViewById(R.id.company_details_container);
        company_description = findViewById(R.id.company_description);
        company_name        = findViewById(R.id.company_name);
        company_number_ofEmployee = findViewById(R.id.number_of_employee);
        company_service     = findViewById(R.id.company_service);
        company_address     = findViewById(R.id.company_address);
        company_barangay    = findViewById(R.id.company_barangay);
        company_city        = findViewById(R.id.company_city);


        //run this method when system start
        systemStart();
    }

    //system start method
    @SuppressLint("ClickableViewAccessibility")
    public void systemStart() {

        //when user click the user profile img
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImagePicker.Builder(register_upload_photos.this)
                        .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.PNG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build();
            }
        });


        //when user click the user valid id
        user_valid_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImagePicker.Builder(register_upload_photos.this)
                        .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.PNG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        //submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user_profile_img_image_string.equals("")) {
                    if (!email.getEditText().getText().toString().equals("")) {
                        presenter.onSubmit(user_profile_img_image_string, email.getEditText().getText().toString(), valid_id_image_string,latitude,longitude,
                                company_number_ofEmployee.getEditText().getText().toString(),
                                company_address.getEditText().getText().toString(),
                                company_barangay.getEditText().getText().toString(),
                                company_service.getSelectedItem().toString(),
                                company_city.getSelectedItem().toString(),
                                company_name.getEditText().getText().toString(),
                                company_description.getEditText().getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please upload your photo!", Toast.LENGTH_LONG).show();
                }
            }
        });


        // This method will allow the user to scroll inside textinputlayout description
        company_description.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.company_description) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

        //check user type
        presenter.validateUserType();
        checkIfGPSON();


    }


    //On Activity of camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            Log.d("Path",mPaths.toString() + " Size "+mPaths.size());
            Bitmap bmImg_0 = BitmapFactory.decodeFile(mPaths.get(0));

            user_profile.setImageBitmap(bmImg_0);

            Log.d("bitmap_data",bmImg_0.toString());
            user_profile_img_image_string = getStringImage(bmImg_0);

        }
    }

    //Convert bitmap to image string
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();


        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



    @Override
    public void showValidId_container(String userType) {
        if (userType.equals("User")) {
            valid_id_container.setVisibility(View.GONE);
            company_details_container.setVisibility(View.GONE);
        } else {
            valid_id_container.setVisibility(View.VISIBLE);
            company_details_container.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void to_StepThree() {
        Intent intent = new Intent(register_upload_photos.this, register_view_b.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    public void checkIfGPSON(){

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager=(LocationManager) getSystemService(getApplication().LOCATION_SERVICE);

        //Check gps is enable or not
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            //Write Function To enable gps
            presenter.enableGPS();
        }
        else
        {
            //GPS is already On then
            getLocation();

        }

    }


    //getting location using network, gps and provider
    private void getLocation() {

        //Check Permissions again
        if (ActivityCompat.checkSelfPermission(register_upload_photos.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(register_upload_photos.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Log.d("GPS","Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);

            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Log.d("GPS","Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Log.d("GPS","Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }


    }


}
