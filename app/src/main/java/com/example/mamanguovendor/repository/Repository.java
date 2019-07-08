package com.example.mamanguovendor.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mamanguovendor.data.models.CancelRequest;
import com.example.mamanguovendor.data.models.CompleteRequest;
import com.example.mamanguovendor.data.models.Requests;
import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.data.network.retrofit.RetrofitClient;
import com.example.mamanguovendor.data.network.retrofit.RetrofitService;
import com.example.mamanguovendor.util.ApplicationContextProvider;
import com.example.mamanguovendor.util.PreferenceUtils;
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
    private MutableLiveData<Requests> requestsLiveData = new MutableLiveData<>();
    private MutableLiveData<CompleteRequest> completeLiveData = new MutableLiveData<>();
    private MutableLiveData<CancelRequest> requestLiveData = new MutableLiveData<>();

    private Repository() {
        retrofitService = RetrofitClient.getInstance().getRetrofitService();
//        this.retrofitService = RetrofitClient.getInstance().getRetrofitService();
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
                    assert response.body() != null;
                    Log.d(LOG_TAG, "Success: " + response.body().getToken());
                    user.setValue(response.body());
                    Context context = ApplicationContextProvider.getContext();
                    PreferenceUtils.setUserToken(context, response.body().getToken());

                } else {
                    if (response.code() == 401) {
                        user.setValue(null);
                    }
                    try {
                        Log.d(LOG_TAG, "eRROR: " + response.errorBody().string());
                        user.setValue(null);
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

    public LiveData<UserClass> signup(String firstName, String lastName, String email,
                                      String mobileNo, String idNo, String location,
                                      String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", firstName);
        jsonObject.addProperty("lastName", lastName);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("phoneNumber", mobileNo);
        jsonObject.addProperty("idNumber", idNo);
        jsonObject.addProperty("location", location);
        jsonObject.addProperty("password", password);

        Call<UserClass> signupCall = retrofitService.signup(jsonObject);
        signupCall.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d(LOG_TAG, "Success: " + response.body().getToken());
                    user.setValue(response.body());
                    Context context = ApplicationContextProvider.getContext();
                    PreferenceUtils.setUserToken(context, response.body().getToken());
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

    public LiveData<Requests> request() {
        String token = PreferenceUtils.getUserToken(ApplicationContextProvider.getContext());
        String authtoken = "Bearer ".concat(token);
        Call<Requests> requestCall = retrofitService.request(authtoken);

        requestCall.enqueue(new Callback<Requests>() {
            @Override
            public void onResponse(Call<Requests> call, Response<Requests> response) {
                if (response.isSuccessful()) {
                        Log.d(LOG_TAG, "Request returned: " +response.body().getFirstName());
                        requestsLiveData.setValue(null);
                        requestsLiveData.setValue(response.body());
                }else {
                    if (response.code() == 404) {
                        requestsLiveData.setValue(null);
                    }
                    try {
                        Log.d(LOG_TAG, "eRROR: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Requests> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return requestsLiveData;
    }

    public LiveData<CancelRequest> cancelRequest(String status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);

        String token = PreferenceUtils.getUserToken(ApplicationContextProvider.getContext());
        String authtoken = "Bearer ".concat(token);
        Call<CancelRequest> requestCall = retrofitService.cancelRequest(authtoken,jsonObject);
        requestCall.enqueue(new Callback<CancelRequest>() {
            @Override
            public void onResponse(Call<CancelRequest> call, Response<CancelRequest> response) {
                if (response.isSuccessful()){
                    Log.d(LOG_TAG, "onResponse: " + response.body().getMessage());
                    requestLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CancelRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return requestLiveData;
    }

    public LiveData<CompleteRequest> completeRequest(String status){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);

        String token = PreferenceUtils.getUserToken(ApplicationContextProvider.getContext());
        String authtoken = "Bearer ".concat(token);
        Call<CompleteRequest> requestsCall = retrofitService.completeRequest(authtoken,jsonObject);
        requestsCall.enqueue(new Callback<CompleteRequest>() {
            @Override
            public void onResponse(Call<CompleteRequest> call, Response<CompleteRequest> response) {
                Log.d(LOG_TAG, "onResponse: " + response.body().getMessage());
                completeLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CompleteRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });


        return completeLiveData;
    }
}
