package com.overseascab.overseascab;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    public SharedPreferences sp;
    public SharedPreferences.Editor ed;

    public PrefManager(Context c) {
        sp = c.getSharedPreferences("overseas", Context.MODE_PRIVATE);
        ed = sp.edit();
    }

    public void setLogin(String email, String psw, boolean login) {
        ed.putBoolean("login", login);
        ed.putString("email", email);
        ed.putString("password", psw);
        ed.commit();
    }

    public void logout() {
        ed.clear();
        ed.commit();
    }

    public String getEmail() {
        return sp.getString("email", "");
    }

    public String getPsw() {
        return sp.getString("password", "");
    }

    public boolean isLoggedIn() {
        return sp.getBoolean("login", false);
    }
}
