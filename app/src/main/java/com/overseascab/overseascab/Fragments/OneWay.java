package com.overseascab.overseascab.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.overseascab.overseascab.R;

public class OneWay extends Fragment {

    public OneWay() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one_way, container, false);
        BottomNavigationView navigationView = (BottomNavigationView) v.findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(navigationitemselected);
        navigationView.setSelectedItemId(R.id.p2p);
        return v;
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationitemselected =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment f;
                    switch (item.getItemId())
                    {
                        case R.id.p2p:
                            f = new PointFragment();
                            loadFragment(f);
//                            Toast.makeText(getContext(), "point to point", Toast.LENGTH_SHORT).show();
                            return true;

                        case R.id.hrly:
                            f = new HourlyFragment();
                            loadFragment(f);
//                            Toast.makeText(getContext(), "Hourly", Toast.LENGTH_SHORT).show();
                            return true;
                    }
                    return false;
                }
            };

    private void loadFragment(Fragment f) {
        getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
