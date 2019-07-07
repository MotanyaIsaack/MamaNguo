package com.example.mamanguovendor.data.models;

import com.google.gson.annotations.SerializedName;

public class UserClass {

    private String token;
    @SerializedName("error")
    private String message;

    public UserClass(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
