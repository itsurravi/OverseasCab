package com.overseascab.overseascab.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.overseascab.overseascab.Fragments.BookingFragment;
import com.overseascab.overseascab.Fragments.LoginFragment;
import com.overseascab.overseascab.Fragments.OneWay;
import com.overseascab.overseascab.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //to be change according to login
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Dynamic menu on navigationview
        Menu m = navigationView.getMenu();
        m.add("BOOKINGS");
        m.add("SHARE");
        m.add("LOGIN");


        //set perticular item to be open from navigationview menu at startup
        onNavigationItemSelected(navigationView.getMenu().getItem(2));
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String name = item.getTitle().toString();
        switch (name)
        {
            case "BOOKINGS":
                toolbar.setTitle("BOOKINGS");
                BookingFragment b = new BookingFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, b).commit();
                Toast.makeText(this, "Bookings", Toast.LENGTH_SHORT).show();
                break;
            case "SHARE":
                toolbar.setTitle("SHARE");
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case "LOGIN":
                toolbar.setTitle("LOGIN");
                LoginFragment l = new LoginFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, l).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
