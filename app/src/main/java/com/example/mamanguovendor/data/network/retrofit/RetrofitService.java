package com.example.mamanguovendor.data.network.retrofit;

import com.example.mamanguovendor.data.models.UserClass;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("login")
    Call<UserClass> login(@Body JsonObject jsonObject);

    @POST("register")
    Call<UserClass> signup(@Body JsonObject jsonObject);
}
