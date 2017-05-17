package com.teamdating.datingapp.Activities;

import android.os.Bundle;
import android.support.annotation.PluralsRes;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonArrayCallback;
import com.studioidan.httpagent.JsonCallback;
import com.teamdating.datingapp.Models.Platform;
import com.teamdating.datingapp.R;
import com.teamdating.datingapp.StorageProvider;
import com.teamdating.datingapp.Models.Platform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DagOmzet extends AppCompatActivity {

    private String mPlatformname;
    private ListView mListView;
    private JSONArray mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dag_omzet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list_view);
        mResults = new JSONArray();

        //getPlatforms();
        getResults();
    };

 /*   private void getPlatform(int id) {
        StorageProvider sp = new StorageProvider(this);
        String token = sp.getToken();
        HttpAgent.get("https://rest-api.janine.project89109.nl/platform/"+id)
                .headers("Authorization", "token " + token, "Content-Type", "application/json")
                .goJsonArray(new JsonArrayCallback() {
                    @Override
                    protected void onDone(boolean success, JSONArray jsonArray) {
                        if (jsonArray != null) {
                            platforms = jsonArray;
                        } else {
                            Toast.makeText(DagOmzet.this, "Geen resultaten van platforms ontvangen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/
 
    private void getResults() {
        StorageProvider sp = new StorageProvider(this);
        String token = sp.getToken();
        HttpAgent.get("https://rest-api.janine.project89109.nl/stats/daily-targets")
                .headers("Authorization", "token " + token, "Content-Type", "application/json")
                .goJsonArray(new JsonArrayCallback() {
                    @Override
                    protected void onDone(boolean success, JSONArray mResults) {
                        if (mResults != null) {
                            showDagomzet(mResults);
                            //return mResults???
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
