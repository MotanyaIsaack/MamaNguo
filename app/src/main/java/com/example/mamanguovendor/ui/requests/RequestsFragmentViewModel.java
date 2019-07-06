package com.example.mamanguovendor.ui.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mamanguovendor.data.models.Requests;
import com.example.mamanguovendor.repository.Repository;

public class RequestsFragmentViewModel extends ViewModel {

    private final Repository repository;

    public RequestsFragmentViewModel() {
        this.repository = Repository.getInstance();
    }

    public LiveData<Requests> getRequest() {
        return repository.request();
    }

}
