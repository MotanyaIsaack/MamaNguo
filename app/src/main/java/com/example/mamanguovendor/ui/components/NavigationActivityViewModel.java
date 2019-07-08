package com.example.mamanguovendor.ui.components;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.repository.Repository;

public class NavigationActivityViewModel extends ViewModel {
    private final Repository repository;

    public NavigationActivityViewModel() {
        this.repository = Repository.getInstance();
    }

    public LiveData<UserClass> logout(){
        return repository.logout();
    }

}
