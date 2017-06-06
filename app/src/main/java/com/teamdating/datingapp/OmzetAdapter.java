package com.teamdating.datingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.teamdating.datingapp.Activities.DagOmzet;
import com.teamdating.datingapp.Models.Platform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class OmzetAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList values;

    public OmzetAdapter(Context context, int resource, ArrayList values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_dag_omzet, parent, false);
        }
        // Lookup view for data population
        ListView listView = (ListView) convertView.findViewById(R.id.list_view);

        // Populate the data into the template view using the data object
        // Return the completed view to render on screen
        return convertView;
    }
}