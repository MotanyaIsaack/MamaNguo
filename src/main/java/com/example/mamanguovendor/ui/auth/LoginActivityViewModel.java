package com.example.mamanguovendor.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.repository.Repository;

public class LoginActivityViewModel extends ViewModel {

    private static final String TAG = LoginActivityViewModel.class.getSimpleName();
    private Repository repository;
//    private MutableLiveData<UserClass> retreivedUser = new MutableLiveData<>();

    public LoginActivityViewModel() {
        this.repository = Repository.getInstance();
    }

    public LiveData<UserClass> login(String mobileNo, String password) {
        Log.d(TAG, password);
        return repository.login(mobileNo, password);
    }
}
