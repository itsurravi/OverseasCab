package com.overseascab.overseascab.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.overseascab.overseascab.Activities.VehiclesActivity;
import com.overseascab.overseascab.R;

import java.util.Calendar;

public class TwoWay extends Fragment {

    EditText ppoint, dpoint, pdate, ptime;
    Button search;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public TwoWay() {
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
        View v = inflater.inflate(R.layout.fragment_two_way, container, false);
        ppoint = (EditText)v.findViewById(R.id.pickup);
        dpoint = (EditText)v.findViewById(R.id.drop);
        pdate = (EditText)v.findViewById(R.id.p_date);
        ptime = (EditText)v.findViewById(R.id.p_time);
        search = (Button)v.findViewById(R.id.search);

        pdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        ptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d,t, pl, dl;
                d = pdate.getText().toString();
                t = ptime.getText().toString();
                pl = ppoint.getText().toString();
                dl = dpoint.getText().toString();
                if(!d.isEmpty() || !t.isEmpty() || !pl.isEmpty() || !dl.isEmpty()) {
                    Intent i = new Intent(getContext(), VehiclesActivity.class);
                    i.putExtra("pickup_location", pl);
                    i.putExtra("drop_location", dl);
                    i.putExtra("date", d);
                    i.putExtra("time", t);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getContext(), "Please Select All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        ptime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        pdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
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
