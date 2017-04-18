package com.teamdating.datingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonCallback;

import org.json.JSONObject;

public class DagOmzet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dag_omzet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getResults();

    };

    private void getResults() {
        StorageProvider sp = new StorageProvider(this);
        HttpAgent.get("https://rest-api.janine.project89109.nl/stats/platforms/token")
                .headers("Authorization", sp.getToken())
                .goJson(new JsonCallback() {
                    @Override
                    protected void onDone(boolean success, JSONObject jsonObject) {
                        String results = getStringResults();
                        org.json.JSONObject jsonResult;
                        String token = null;

                        try {
                            jsonResult = new JSONObject(results);
                            token = jsonResult.getString("token").toString();
                        } catch (Throwable t) {
                        }

                        if (!TextUtils.isEmpty(token)) {
                            // show results
                        } else {
                            Toast.makeText(DagOmzet.this, "Geen resultaten", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
