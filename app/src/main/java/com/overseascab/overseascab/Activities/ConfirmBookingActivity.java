package com.overseascab.overseascab.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.overseascab.overseascab.Links;
import com.overseascab.overseascab.PrefManager;
import com.overseascab.overseascab.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConfirmBookingActivity extends AppCompatActivity implements View.OnClickListener{

    TextView carname, pick_location, date_time, username, email, contact;
    ImageView carimage;
    Button confirm;

    PrefManager p;

    String car_name, picklocation, datetime, car_image,
            user_name, user_email, firstname, lastname, mobile, position;

    ProgressDialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);

        carname = (TextView)findViewById(R.id.car_name);
        pick_location = (TextView)findViewById(R.id.p_location);
        date_time = (TextView)findViewById(R.id.date_time);
        username = (TextView)findViewById(R.id.user_name);
        email = (TextView)findViewById(R.id.user_email);
        contact = (TextView)findViewById(R.id.user_contact);
        carimage = (ImageView)findViewById(R.id.car_img);

        d = new ProgressDialog(this);
        d.setCancelable(false);
        d.setMessage("Making Your Booking...");

        Bundle b = getIntent().getExtras();
        car_name = b.getString("carname");
        car_image = b.getString("carimage");
        picklocation = b.getString("pick");
        datetime = b.getString("date_time");
        position = b.getString("position");

        carname.setText(car_name);
        pick_location.setText(picklocation);
        date_time.setText(datetime);

        Glide.with(this).load(car_image).into(carimage);

        confirm = (Button)findViewById(R.id.confirm);

        p = new PrefManager(this);

        confirm.setOnClickListener(this);

        getUserInfo();
    }

    private void getUserInfo() {
        d.show();
        final String email = p.getEmail();
        final String psw = p.getPsw();
//        Toast.makeText(this, email+" "+psw, Toast.LENGTH_SHORT).show();
        StringRequest sr = new StringRequest(Request.Method.POST, Links.userData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showData(response);
                       // Toast.makeText(ConfirmBookingActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmBookingActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                        d.dismiss();
                        finish();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put("email", email);
                m.put("psw", psw);
                return m;
            }
        };

        RequestQueue r = Volley.newRequestQueue(this);
        r.add(sr);

    }

    private void showData(String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("result");

            JSONObject o = array.getJSONObject(0);

            user_name = o.getString("username");
            user_email = o.getString("email");
            firstname = o.getString("firstname");
            lastname = o.getString("lastname");
            mobile = o.getString("mobile");

            String name = firstname+" "+lastname;
            username.setText(name);
            email.setText(user_email);
            contact.setText(mobile);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        final ProgressDialog d = new ProgressDialog(this);
        d.setCancelable(false);
        d.setMessage("Making your Booking...");
        d.show();
        StringRequest sr = new StringRequest(Request.Method.POST, Links.updatebooking,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("Booking Updated"))
                        {
                            Toast.makeText(ConfirmBookingActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ConfirmBookingActivity.this, BookingDone.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(ConfirmBookingActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                        d.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmBookingActivity.this, "Please check your internet connection\nThen Try Again", Toast.LENGTH_LONG).show();
                        d.dismiss();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put("id", position);
                m.put("booked", "1");
                return m;
            }
        };

        RequestQueue r = Volley.newRequestQueue(this);
        r.add(sr);
    }
}
