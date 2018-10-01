package com.overseascab.overseascab.Fragments;

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

import com.overseascab.overseascab.Activities.SignUpActivity;
import com.overseascab.overseascab.R;


public class LoginFragment extends Fragment implements View.OnClickListener{

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
        if(v==login)
        {
            String em = email.getText().toString();
            String psw = ps.getText().toString();
            /*
            * Need to call API for login.
            * */
            Toast.makeText(getContext(), em+"\n"+psw, Toast.LENGTH_SHORT).show();
        }
        if(v==signup)
        {
            startActivity(new Intent(getContext(), SignUpActivity.class));
        }
//        if(v==forget)
//        {
//
//        }
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
