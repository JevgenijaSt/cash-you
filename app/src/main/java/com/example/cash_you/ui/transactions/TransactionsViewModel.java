package com.example.cash_you.ui.transactions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransactionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TransactionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You do not have any transactions in this month");
    }

    public LiveData<String> getText() {
        return mText;
    }
}