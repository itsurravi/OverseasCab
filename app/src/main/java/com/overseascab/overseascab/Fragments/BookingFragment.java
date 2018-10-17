package com.overseascab.overseascab.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overseascab.overseascab.R;


public class BookingFragment extends Fragment {

    TabLayout t;
    ViewPager vp;

    Pager p;

    public BookingFragment() {
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
        View v = inflater.inflate(R.layout.fragment_booking, container, false);
        t = (TabLayout)v.findViewById(R.id.tab);
        vp= (ViewPager)v.findViewById(R.id.viewPager);

        p = new Pager(getFragmentManager());

        vp.setAdapter(p);

        t.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp));
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(t));

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    class Pager extends FragmentStatePagerAdapter
    {

        public Pager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new OneWay()/*.newInstance("OneWay", "1")*/;
                case 1:
                    return new TwoWay()/*.newInstance("OneWay", "1")*/;
                default:
                    return new OneWay()/*.newInstance("OneWay", "1")*/;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
