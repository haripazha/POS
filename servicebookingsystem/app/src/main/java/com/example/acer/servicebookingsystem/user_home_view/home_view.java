package com.example.acer.servicebookingsystem.user_home_view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.background_services.background_services;
import com.example.acer.servicebookingsystem.provider_home_view.menu_service.menu_service_adapter.menu_service_adapter;
import com.example.acer.servicebookingsystem.user_home_view.home_viewMenu.home_viewFilter;
import com.example.acer.servicebookingsystem.user_home_view.home_viewMenu.home_viewSearch;
import com.example.acer.servicebookingsystem.user_home_view.menu_booking.menu_booking;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.adapter.menu_home_adapter;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.menu_home;
import com.example.acer.servicebookingsystem.user_login_view.user_login_view;
import com.example.acer.servicebookingsystem.user_register_view.register_view_a.register_upload_photos;

import java.util.ArrayList;

public class home_view extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,home_viewContract.home_view,menu_home.OnFragmentInteractionListener, menu_booking.OnFragmentInteractionListener {

    //mvp declaration
    home_viewPresenter presenter;

    //location declaration
    LocationManager locationManager;
    String latitude,longitude;

    //Request code declaration
    private static  final int REQUEST_LOCATION=1;

    //for instance declaration
    public static home_view instance;

    //view declaration
    View header;

    //Menu Declaration
    Menu menu;


    //object declaration
    ImageView user_img_view;
    TextView user_name_view;
    TextView user_email_view;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new home_viewPresenter(this);
        instance = this;

        //toggle of drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //navigation declaration
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header = navigationView.inflateHeaderView(R.layout.nav_header_home_view);
        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        navigationView.setItemIconTintList(null);

        //object declaration
        user_img_view       = header.findViewById(R.id.user_img);
        user_name_view      = header.findViewById(R.id.user_name);
        user_email_view     = header.findViewById(R.id.user_email);



        //run this method when system start
        systemStart();
    }


    //system start method
    public void systemStart(){
        //start service
        presenter.startService();

        //get location
        getLocation();

        //getUser details
        presenter.getUser_details();

        //set Default
        //replacing the old fragment to home sale fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new menu_home());
        ft.commit();
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Home</font>"));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            presenter.onLogout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_view, menu);
        this.menu = menu;


        //Menu Setting
        final MenuItem menu_search = menu.findItem(R.id.service_search);
        menu_search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //Search item
                home_viewSearch home_search = new home_viewSearch();
                home_search.show(getSupportFragmentManager(), "example dialog");

                return false;
            }
        });

        MenuItem menu_filter = menu.findItem(R.id.service_filter);
        menu_filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //filter item
                home_viewFilter home_search = new home_viewFilter();
                home_search.show(getSupportFragmentManager(), "example dialog");

                return false;
            }
        });

        MenuItem menu_notification = menu.findItem(R.id.service_notification);
        menu_notification.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {

            //replacing the old fragment to home sale fragment
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new menu_home());
            ft.commit();
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Home</font>"));


        } else if (id == R.id.nav_booking) {

            //replacing the old fragment to home sale fragment
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new menu_booking());
            ft.commit();
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Booking</font>"));


        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_logout) {
            presenter.onLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //getting location using network, gps and provider
    public void getLocation() {

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager=(LocationManager) getSystemService(getApplication().LOCATION_SERVICE);


        //Check Permissions again
        if (ActivityCompat.checkSelfPermission(home_view.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(home_view.this,

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
        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onExit() {
        Intent intent = new Intent(home_view.this,user_login_view.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void populateDrawableContent(String name, String email, String img_name) {
        user_name_view.setText(name);
        user_email_view.setText(email);

    }

}
