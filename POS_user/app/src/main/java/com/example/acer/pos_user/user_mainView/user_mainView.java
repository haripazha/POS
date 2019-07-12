package com.example.acer.pos_user.user_mainView;

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

import com.example.acer.pos_user.R;
import com.example.acer.pos_user.user_login.user_login;
import com.example.acer.pos_user.user_mainView.fragment.my_order.my_order;
import com.example.acer.pos_user.user_mainView.fragment.order.order;
import com.example.acer.pos_user.user_mainView.fragment.order_history.order_history;

public class user_mainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, my_order.OnFragmentInteractionListener, order.OnFragmentInteractionListener,user_mainViewContract.user_mainView,
order_history.OnFragmentInteractionListener{

    //mvp declaration
    user_mainViewPresenter presenter;
    public static user_mainView instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instance = this;
        //mvp declaration
        presenter = new user_mainViewPresenter(this);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //System start
        systemStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myOrder) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new my_order());
            ft.commit();
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>My Orders</font>"));

        } else if (id == R.id.nav_order) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new order());
            ft.commit();
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Order</font>"));

        } else if (id == R.id.nav_orderHistory) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new order_history());
            ft.commit();
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Order History</font>"));

        } else if (id == R.id.nav_option) {



        } else if (id == R.id.nav_my_profile) {



        } else if (id == R.id.nav_logout) {
            presenter.onLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void systemStart(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new my_order());
        ft.commit();
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>My Orders</font>"));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    //to login after logout
    @Override
    public void toLogin() {
        Intent intent = new Intent(getApplicationContext(),user_login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    //goTo Order
    public void my_orderProcessed(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new order());
        ft.commit();
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' align='center'>Order</font>"));
    }
}
