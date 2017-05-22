package com.teamdating.datingapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonArrayCallback;
import com.teamdating.datingapp.R;
import com.teamdating.datingapp.StorageProvider;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DagOmzet extends AppCompatActivity {

    JSONArray platforms = new JSONArray();
    ArrayList stats = new ArrayList();
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dag_omzet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView) findViewById(R.id.list_view);
        setSupportActionBar(toolbar);
        getPlatforms();

    }

    private void getPlatforms() {
        StorageProvider sp = new StorageProvider(this);
        String token = sp.getToken();
        HttpAgent.get("https://rest-api.janine.project89109.nl/platforms")
                .headers("Authorization", "token " + token, "Content-Type", "application/json")
                .goJsonArray(new JsonArrayCallback() {
                    @Override
                    protected void onDone(boolean success, JSONArray jsonArray) {
                        if (jsonArray != null) {
                            platforms = jsonArray;
                            getResults();
                        } else {
                            Toast.makeText(DagOmzet.this, "Geen resultaten van platforms ontvangen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getResults() {
        StorageProvider sp = new StorageProvider(this);
        String token = sp.getToken();
        HttpAgent.get("https://rest-api.janine.project89109.nl/stats/daily-targets")
                .headers("Authorization", "token " + token, "Content-Type", "application/json")
                .goJsonArray(new JsonArrayCallback() {
                    @Override
                    protected void onDone(boolean success, JSONArray jsonArray) {
                        if (jsonArray != null) {
                            jsonArray = addPlatformsToStats(jsonArray);
                            jsonToList(jsonArray);
                                //TODO ADD jsonArray TO ARRAYADAPTER and set it to the listview




                        } else {
                            Toast.makeText(DagOmzet.this, "Geen resultaten van daily-targets ontvangen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private JSONArray addPlatformsToStats(JSONArray jsonArray) {
        for (int i = 0; i < platforms.length(); i++) {
            for(int p = 0; p < jsonArray.length(); p++) {
                try {
                    if (platforms.getJSONObject(i).getInt("id") == jsonArray.getJSONObject(p).getInt("platformId")) {
                        jsonArray.getJSONObject(p).put("platform",platforms.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }

    private void jsonToList(JSONArray jsonArray) {
        for(int p = 0; p < jsonArray.length(); p++) {
            try {
                stats.add(jsonArray.getJSONObject(p));
                Toast.makeText(DagOmzet.this, stats.get(0).toString(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDagomzet(JSONArray jsonArray) {
        Toast.makeText(DagOmzet.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
    }
}
