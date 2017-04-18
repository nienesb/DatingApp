package com.teamdating.datingapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Janine on 18-4-2017.
 */

public class JsonConverter {

    public String getString (JSONObject jsonResult, String jsonObject) throws JSONException {
        String strResult;
        strResult = jsonResult.getString(jsonObject).toString();
        if (strResult == null) {
            return "";
        }
        return strResult;
    };
}
