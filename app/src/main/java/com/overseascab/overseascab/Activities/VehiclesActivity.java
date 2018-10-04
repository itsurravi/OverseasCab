package com.overseascab.overseascab.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.overseascab.overseascab.Adapters.VehicleAdapter;
import com.overseascab.overseascab.Models.Vehicles;
import com.overseascab.overseascab.R;

import java.util.ArrayList;
import java.util.List;


public class VehiclesActivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);

        setTitle("Select Vehicle");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        rv = (RecyclerView)findViewById(R.id.vehicles_list);

        List<Vehicles> l = new ArrayList<>();

        for(int i=0;i<10;i++)
        {
            Vehicles v = new Vehicles("abc "+i,"abc "+i,"abc "+i,"abc "+i,"abc "+i,"abc "+i);
            l.add(v);
        }

        VehicleAdapter adapter = new VehicleAdapter(this, l);
        rv.setAdapter(adapter);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(lm);

        rv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
