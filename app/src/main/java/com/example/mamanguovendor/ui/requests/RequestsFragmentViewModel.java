package com.example.mamanguovendor.ui.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mamanguovendor.data.models.CancelRequest;
import com.example.mamanguovendor.data.models.CompleteRequest;
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

    public LiveData<CancelRequest> cancelRequest(String status){
        return repository.cancelRequest(status);
    }

    public LiveData<CompleteRequest> completeRequests(String status){
        return repository.completeRequest(status);
    }

}
