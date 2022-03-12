package com.firstapp.firebaseauthproject.ui.vitals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VitalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VitalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}