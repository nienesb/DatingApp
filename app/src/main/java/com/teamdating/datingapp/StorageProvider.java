package com.teamdating.datingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class StorageProvider extends AppCompatActivity {

    private SharedPreferences prefs;

    public StorageProvider (Context context) {
        prefs = context.getSharedPreferences(
                "com.teamdating.datingapp", Context.MODE_PRIVATE);
    }

    public void setToken (String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken () {
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString("token", "");
    }

}
