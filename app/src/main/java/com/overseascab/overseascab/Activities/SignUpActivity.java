package com.overseascab.overseascab.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.overseascab.overseascab.Links;
import com.overseascab.overseascab.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText un, fn, ln, em, ps, cps, pn;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("SIGN UP");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        un = (EditText) findViewById(R.id.user_name);
        fn = (EditText) findViewById(R.id.f_name);
        ln = (EditText) findViewById(R.id.l_name);
        em = (EditText) findViewById(R.id.email);
        ps = (EditText) findViewById(R.id.password);
        cps = (EditText) findViewById(R.id.cpassword);
        pn = (EditText) findViewById(R.id.m_number);

        submit = (Button) findViewById(R.id.register);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void register() {
        final String u_name, f_name, l_name, email, psw, cpsw, phone;
        u_name = un.getText().toString().trim();
        f_name = fn.getText().toString().trim();
        l_name = ln.getText().toString().trim();
        email = em.getText().toString().trim();
        psw = ps.getText().toString().trim();
        cpsw = cps.getText().toString().trim();
        phone = pn.getText().toString().trim();

        if (u_name.isEmpty() || f_name.isEmpty() || l_name.isEmpty() || email.isEmpty() ||
                psw.isEmpty() || cpsw.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            em.setError("Email Id is INVALID");
        } else if (ps.length() < 8) {
            ps.setError("Length must be more than 8");
        } else if (!psw.equals(cpsw)) {
            cps.setError("Password is not Matching");
        } else if (pn.length() != 10) {
            pn.setError("Phone Number INVALID");
        } else {

            final ProgressDialog d = new ProgressDialog(this);
            d.setMessage("Please Wait");
            d.setCancelable(false);
            d.show();
            StringRequest r = new StringRequest(Request.Method.POST, Links.signup,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            d.dismiss();
                            if(response.contains("Registration Successfull")) {
                                Toast.makeText(SignUpActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(SignUpActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignUpActivity.this, "Check Internet and Try Again", Toast.LENGTH_SHORT).show();
                            d.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> m = new HashMap<>();
                    m.put("username", u_name);
                    m.put("email", email);
                    m.put("fname", f_name);
                    m.put("lname", l_name);
                    m.put("psw", psw);
                    m.put("mobile", phone);
                    return m;
                }
            };

            RequestQueue rq = Volley.newRequestQueue(this);
            rq.add(r);
        }

    }
}
