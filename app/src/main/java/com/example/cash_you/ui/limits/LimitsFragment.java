package com.example.cash_you.ui.limits;

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

public class LimitsFragment extends Fragment {

    private LimitsViewModel limitsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        limitsViewModel =
                new ViewModelProvider(this).get(LimitsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_limits, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        limitsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}