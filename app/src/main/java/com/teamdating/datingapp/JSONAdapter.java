package com.teamdating.datingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONAdapter {


    // method converts JSONArray to List of Maps
    protected static List<Map<String, String>> getListFromJsonArray(JSONArray jsonArray) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        // fill the list
        for (int i = 0; i < jsonArray.length(); i++) {
            map = new HashMap<String, String>();
            try {
                JSONObject jo = (JSONObject) jsonArray.get(i);
                // fill map
                Iterator iter = jo.keys();
                while (iter.hasNext()) {
                    String currentKey = (String) iter.next();
                    map.put(currentKey, jo.getString(currentKey));
                }
                // add map to list
                list.add(map);
            } catch (JSONException e) {
                Log.e("JSON", e.getLocalizedMessage());
            }
        }
        return list;
    }
}