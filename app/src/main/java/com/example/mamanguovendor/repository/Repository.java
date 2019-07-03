package com.example.mamanguovendor.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.data.network.retrofit.RetrofitClient;
import com.example.mamanguovendor.data.network.retrofit.RetrofitService;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static final String LOG_TAG = Repository.class.getSimpleName();
    private static Repository instance;
    private final RetrofitService retrofitService;
    private MutableLiveData<UserClass> user = new MutableLiveData<>();

    private Repository() {
        this.retrofitService = RetrofitClient.getInstance().getRetrofitService();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public LiveData<UserClass> login(String mobileNo, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phoneNumber", mobileNo);
        jsonObject.addProperty("password", password);

        Call<UserClass> loginCall = retrofitService.login(jsonObject);
        loginCall.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                if (response.isSuccessful()) {
                    Log.d(LOG_TAG, "Success: " + response.body().getToken());
                    user.setValue(response.body());
                } else {
                    if (response.code() == 401) {
                        user.setValue(null);
                    }
                    try {
                        Log.d(LOG_TAG, "eRROR: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserClass> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return user;
    }
}
