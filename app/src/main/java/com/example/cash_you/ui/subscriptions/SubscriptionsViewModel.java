package com.example.cash_you.ui.subscriptions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SubscriptionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SubscriptionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You do not have any subscriptions");
    }

    public LiveData<String> getText() {
        return mText;
    }
}