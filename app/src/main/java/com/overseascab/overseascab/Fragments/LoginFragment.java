package com.overseascab.overseascab.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.overseascab.overseascab.Activities.HomeActivity;
import com.overseascab.overseascab.Activities.SignUpActivity;
import com.overseascab.overseascab.Links;
import com.overseascab.overseascab.PrefManager;
import com.overseascab.overseascab.R;

import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText email, ps;
    Button login;
    TextView signup, forget;

    public LoginFragment() {
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
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        email = v.findViewById(R.id.email);
        ps = v.findViewById(R.id.password);
        login = v.findViewById(R.id.login);
        signup = v.findViewById(R.id.signup);
//        forget = v.findViewById(R.id.forget);


        login.setOnClickListener(this);
        signup.setOnClickListener(this);
//        forget.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            String em = email.getText().toString();
            String psw = ps.getText().toString();
            if(!(em.isEmpty() || psw.isEmpty())) {
                user_login(em, psw);
            }
            else
            {
                Toast.makeText(getContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(getContext(), em+"\n"+psw, Toast.LENGTH_SHORT).show();
        }
        if (v == signup) {
            startActivity(new Intent(getContext(), SignUpActivity.class));
        }
    }

    private void user_login(final String em, final String psw) {
        final ProgressDialog d = new ProgressDialog(getContext());
        d.setCancelable(false);
        d.setMessage("Logging In...");
        d.show();

        StringRequest r = new StringRequest(Request.Method.POST, Links.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("Login Successfull")) {
                            PrefManager p = new PrefManager(getContext());
                            p.setLogin(em, psw, true);
                            d.dismiss();
                            startActivity(new Intent(getContext(), HomeActivity.class));
                            getActivity().finish();
                            Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                            d.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Check Internet and Try Again", Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put("email", em);
                m.put("psw", psw);
                return m;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(getContext());
        rq.add(r);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
