package com.example.mamanguovendor.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.repository.Repository;

class RegistrationActivityViewModel extends ViewModel {

    private static final String TAG = RegistrationActivityViewModel.class.getSimpleName();
    private final Repository repository;

    public RegistrationActivityViewModel(){
        this.repository = Repository.getInstance();
    }

    public LiveData<UserClass> signup(String firstName, String lastName, String email,
                                      String mobileNo, String idNo, String location,
                                      String password){
        return repository.signup(firstName,lastName,email,mobileNo,idNo,location,password);
    }
}
