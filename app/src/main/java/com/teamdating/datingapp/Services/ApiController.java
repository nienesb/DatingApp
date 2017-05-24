package com.teamdating.datingapp.Services;
import android.widget.Toast;

import java.util.List;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdating.datingapp.Activities.LoginActivity;
import com.teamdating.datingapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by BT on 5/24/2017.
 */

public class ApiController implements Callback<User> {

    static final String BASE_URL = "https://rest-api.janine.project89109.nl/";

    public User Login(String username, String password) {
        final User[] user = {new User()};

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> call = apiService.login(username, password);
        call.enqueue(new Callback<User>() {


            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int statusCode = response.code();
                user[0] = response.body();
                //Gson gson = new Gson();
                //user[0] = gson.fromJson(String.valueOf(response.body()), User.class);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                HttpException response = (HttpException)t;
                int code = response.code();
            }
        });

        return user[0];
    }

    /*@Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        if(response.isSuccessful()) {
            List<Change> changesList = response.body();
            changesList.forEach(change -> System.out.println(change.subject));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        t.printStackTrace();
    }*/

    @Override
    public void onResponse(Call<User> call, Response<User> response) {

    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {

    }
}
