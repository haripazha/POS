package com.example.acer.servicebookingsystem.provider_home_view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
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
import android.widget.TextView;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.provider_home_view.menu_service.menu_service;
import com.example.acer.servicebookingsystem.user_home_view.home_view;
import com.example.acer.servicebookingsystem.user_home_view.home_viewMenu.home_viewFilter;
import com.example.acer.servicebookingsystem.user_home_view.home_viewMenu.home_viewSearch;
import com.example.acer.servicebookingsystem.user_home_view.menu_home.menu_home;
import com.example.acer.servicebookingsystem.user_login_view.user_login_view;

public class provider_home_view extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,provider_homeContract.provider_homeView, menu_home.OnFragmentInteractionListener,
menu_service.OnFragmentInteractionListener{

    //mvp declaration
    provider_homePresenter presenter;

    //view declaration
    View header;

    //object declaration
    ImageView user_img_view;
    TextView user_name_view;
    TextView user_email_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new provider_homePresenter(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header = navigationView.inflateHeaderView(R.layout.nav_header_home_view);
        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        navigationView.setItemIconTintList(null);

        //object declaration
        user_img_view       = header.findViewById(R.id.user_img);
        user_name_view      = header.findViewById(R.id.user_name);
        user_email_view     = header.findViewById(R.id.user_email);


        //systemStart
        systemStart();
    }

    //run this method when system start
    public void systemStart(){

        //getUser details
        presenter.getUser_details();

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

        getMenuInflater().inflate(R.menu.provider_home_view, menu);


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

/*
        if (id == R.id.action_settings) {
            return true;
        }
*/
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

        } else if (id == R.id.nav_service) {

            //replacing the old fragment to home sale fragment
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new menu_service());
            ft.commit();
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Your Services</font>"));

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_profile) {

        }else if (id == R.id.nav_logout) {
            presenter.onLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //when system logout
    @Override
    public void onExit() {
        Intent intent = new Intent(provider_home_view.this,user_login_view.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void populateDrawableContent(String name, String email, String img_name) {
        user_name_view.setText(name);
        user_email_view.setText(email);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
