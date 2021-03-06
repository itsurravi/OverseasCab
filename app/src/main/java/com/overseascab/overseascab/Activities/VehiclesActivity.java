package com.overseascab.overseascab.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.overseascab.overseascab.Adapters.VehicleAdapter;
import com.overseascab.overseascab.Links;
import com.overseascab.overseascab.Models.Vehicles;
import com.overseascab.overseascab.ParserJSON;
import com.overseascab.overseascab.PrefManager;
import com.overseascab.overseascab.R;

import java.util.ArrayList;
import java.util.List;


public class VehiclesActivity extends AppCompatActivity implements VehicleAdapter.OnItemClickListener {

    RecyclerView rv;
    VehicleAdapter v;
    ProgressDialog d;

    List<Vehicles> l;

    String pickup_location, drop_location, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PrefManager m = new PrefManager(this);
        if(!m.isLoggedIn())
        {
            finish();
            Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
        }

        setContentView(R.layout.activity_vehicles);

        setTitle("Select Vehicle");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rv = (RecyclerView) findViewById(R.id.vehicles_list);

        Bundle b = getIntent().getExtras();
        if(b.size()==4)
        {
            pickup_location = b.getString("pickup_location");
            drop_location = b.getString("drop_location");
            date = b.getString("date");
            time = b.getString("time");
        }
        else if(b.size()==3)
        {
            pickup_location = b.getString("pickup_location");
            date = b.getString("date");
            time = b.getString("time");
        }

        d = new ProgressDialog(this);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(lm);

        rv.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchData();
    }

    public void fetchData() {
        d.setCancelable(false);
        d.setMessage("Loading...");
        d.show();
        StringRequest sr = new StringRequest(Links.vehicle_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJson(response);
//                Toast.makeText(VehiclesActivity.this, ""+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                d.dismiss();
                Toast.makeText(VehiclesActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue r = Volley.newRequestQueue(this);
        r.add(sr);
    }

    private void showJson(String response) {
        ParserJSON j = new ParserJSON(response);
        l = j.parseJSON();
        v = new VehicleAdapter(this, l);
        rv.setAdapter(v);
        v.setOnClick(this);
        d.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, ConfirmBookingActivity.class);
        i.putExtra("carname", l.get(position).getCarname());
        i.putExtra("carimage", l.get(position).getCarimage());
        i.putExtra("pick", pickup_location);
        i.putExtra("date_time", date+" "+time);
        i.putExtra("position", String.valueOf(position+1));
        startActivity(i);
        finish();
    }
}
