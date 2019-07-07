package com.example.mamanguovendor.util;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;

public class FormValidators {
    //FirstName and LastName and Location
    public static boolean isNameValid(Editable text) {
        return !TextUtils.isEmpty(text);
    }

    //Email
    public static boolean isEmailValid(Editable text) {
        return !TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    //Password
    public static boolean isPasswordValid(Editable text) {
        return !TextUtils.isEmpty(text) && text.length() >= 4;
    }

    //ID Number
    public static boolean isIDNumberValid(Editable text) {
        return !TextUtils.isEmpty(text) && text.length() >= 5;
    }

    //PhoneNumber
    public static boolean isPhoneNumberValid(Editable phoneNumber) {
        return (phoneNumber.length() >= 8) && Patterns.PHONE.matcher(phoneNumber).matches();
    }
}
