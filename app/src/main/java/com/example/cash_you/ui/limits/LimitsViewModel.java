package com.example.cash_you.ui.limits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LimitsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LimitsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You do not have any limits.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}