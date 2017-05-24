package com.teamdating.datingapp.Services;

import com.teamdating.datingapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by BT on 5/24/2017.
 */

public interface ApiService {
    @GET("authentication/token")
    Call<User> login(@Query("username") String username, @Query("password") String password);
}
