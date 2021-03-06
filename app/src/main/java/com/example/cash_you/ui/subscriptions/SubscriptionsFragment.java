package com.example.cash_you.ui.subscriptions;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cash_you.R;
import com.example.cash_you.ui.limits.LimitsViewModel;

public class SubscriptionsFragment extends Fragment {

    private SubscriptionsViewModel subscriptionsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        subscriptionsViewModel =
                new ViewModelProvider(this).get(SubscriptionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_subscriptions, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        subscriptionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}