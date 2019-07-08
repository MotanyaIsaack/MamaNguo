package com.example.mamanguovendor.data.network.retrofit;

import com.example.mamanguovendor.data.models.CancelRequest;
import com.example.mamanguovendor.data.models.CompleteRequest;
import com.example.mamanguovendor.data.models.Requests;
import com.example.mamanguovendor.data.models.UserClass;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("login")
    Call<UserClass> login(@Body JsonObject jsonObject);

    @POST("register")
    Call<UserClass> signup(@Body JsonObject jsonObject);

    @GET("request")
    Call<Requests> request(@Header("Authorization") String authToken);

    @POST("cancelRequest")
    Call<CancelRequest> cancelRequest(@Header("Authorization") String authToken, @Body JsonObject jsonObject);

    @POST("completeRequest")
    Call<CompleteRequest> completeRequest(@Header("Authorization") String authToken, @Body JsonObject jsonObject);
}
