package com.example.mamanguovendor.data.models;

import com.google.gson.annotations.SerializedName;

public class UserClass {

    private String token;
    @SerializedName("error")
    private String message;
    private String response;

    public UserClass(String token, String message, String response) {
        this.token = token;
        this.message = message;
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getResponse() {
        return response;
    }
}
