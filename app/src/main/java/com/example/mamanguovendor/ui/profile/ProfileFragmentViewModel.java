package com.example.mamanguovendor.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mamanguovendor.data.models.MamaNguo;
import com.example.mamanguovendor.repository.Repository;

public class ProfileFragmentViewModel extends ViewModel {

    private final Repository repository;

    public ProfileFragmentViewModel() {
        this.repository = Repository.getInstance();
    }

    public LiveData<MamaNguo> getMamaNguo(){
        return repository.getMamaNguo();
    }
}
