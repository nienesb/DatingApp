package com.teamdating.datingapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonArrayCallback;
import com.studioidan.httpagent.JsonCallback;
import com.teamdating.datingapp.JSONAdapter;
import com.teamdating.datingapp.R;
import com.teamdating.datingapp.StorageProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
                            for (int i = 0; i < platforms.length(); i++) {
                                for(int p = 0; p < jsonArray.length(); p++) {
                                    try {
                                        if (platforms.getJSONObject(i).getInt("id") == jsonArray.getJSONObject(p).getInt("platformId")) {
                                            jsonArray.getJSONObject(p).put("platform",platforms.getJSONObject(i));
                                            //Toast.makeText(DagOmzet.this, "HET IS GELUKT!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            for(int p = 0; p < jsonArray.length(); p++) {
                                try {
                                    stats.add(jsonArray.getJSONObject(p));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
//                            Object object = stats.get(0);
                                try {
                                    Toast.makeText(DagOmzet.this, jsonArray.getJSONObject(0).get("startDate").toString(), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

//                            String[] from = new String[] {"id"};
//                            int[] to = new int[] { R.id.list_view };
//                            JSONAdapter jsonAdapter = new JSONAdapter(DagOmzet.this, jsonArray, R.layout.content_dag_omzet, from, to );
//                            mListView.setAdapter(jsonAdapter);

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
