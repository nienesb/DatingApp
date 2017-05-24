package com.teamdating.datingapp.Models;

/**
 * Created by BT on 5/24/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("claimSet")
    @Expose
    private ClaimSet claimSet;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ClaimSet getClaimSet() {
        return claimSet;
    }

    public void setClaimSet(ClaimSet claimSet) {
        this.claimSet = claimSet;
    }

}