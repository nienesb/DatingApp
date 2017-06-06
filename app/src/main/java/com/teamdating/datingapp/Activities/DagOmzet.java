package com.teamdating.datingapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonArrayCallback;
import com.teamdating.datingapp.Models.DailyTarget;
import com.teamdating.datingapp.Models.User;
import com.teamdating.datingapp.OmzetAdapter;
import com.teamdating.datingapp.R;
import com.teamdating.datingapp.StorageProvider;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DagOmzet extends AppCompatActivity {

    JSONArray platforms = new JSONArray();
    ArrayList jsonArray = new ArrayList();
    private ListView mListView;

    Gson gson = new Gson();
    List<DailyTarget> dailyTargetList = new ArrayList<DailyTarget>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dag_omzet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //set view
        OmzetAdapter adapter = new OmzetAdapter(this, R.layout.activity_dag_omzet, jsonArray);
        mListView = (ListView) findViewById(R.id.list_view);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPlatforms();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == 16908332) { // TODO: Find a way to get the back button id?, for now hardcoded
            Intent intent = new Intent(DagOmzet.this, MenuActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                            createDailyTargetList(jsonArray);
                        } else {
                            Toast.makeText(DagOmzet.this, "Geen resultaten van daily-targets ontvangen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createDailyTargetList(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                DailyTarget dailyTarget = gson.fromJson(jsonArray.get(i).toString(), DailyTarget.class);
                dailyTargetList.add(dailyTarget);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONArray addPlatformsToStats(JSONArray jsonArray) {
        for (int i = 0; i < platforms.length(); i++) {
            for (int p = 0; p < jsonArray.length(); p++) {
                try {
                    if (platforms.getJSONObject(i).getInt("id") == jsonArray.getJSONObject(p).getInt("platformId")) {
                        jsonArray.getJSONObject(p).put("platform", platforms.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }
}
