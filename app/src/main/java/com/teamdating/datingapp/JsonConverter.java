package com.teamdating.datingapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Janine on 18-4-2017.
 */

public class JsonConverter {

    public String getString(JSONObject jsonResult, String jsonObject) throws JSONException {
        String strResult;
        strResult = jsonResult.getString(jsonObject).toString();
        if (strResult == null) {
            return "";
        }
        return strResult;
    }

    public int getInt(JSONObject jsonResult, String jsonObject) throws JSONException {
        int intResult;
        intResult = jsonResult.getInt(jsonObject);
        return intResult;
    }

    public double getDouble(JSONObject jsonResult, String jsonObject) throws JSONException {
        double doubleResult;
        doubleResult = jsonResult.getInt(jsonObject);
        doubleResult = round(doubleResult, 2);
        return doubleResult;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
