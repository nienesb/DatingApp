package com.teamdating.datingapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Toast;

import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonArrayCallback;
import com.studioidan.httpagent.JsonCallback;
import com.teamdating.datingapp.R;
import com.teamdating.datingapp.StorageProvider;

import org.json.JSONArray;
import org.json.JSONObject;

public class DagOmzet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dag_omzet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getResults();

    }

    ;

    private void getResults() {
        StorageProvider sp = new StorageProvider(this);
        String token = sp.getToken();
        HttpAgent.get("https://rest-api.janine.project89109.nl/stats/daily-targets")
                .headers("Authorization", "token " + token, "Content-Type", "application/json")
                .goJsonArray(new JsonArrayCallback() {
                    @Override
                    protected void onDone(boolean success, JSONArray jsonArray) {

                        if (jsonArray != null) {
                            showDagomzet(jsonArray);
                        } else {
                            Toast.makeText(DagOmzet.this, "Geen resultaten van daily-targets ontvangen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showDagomzet(JSONArray jsonArray) {
        Toast.makeText(DagOmzet.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
    }
}
