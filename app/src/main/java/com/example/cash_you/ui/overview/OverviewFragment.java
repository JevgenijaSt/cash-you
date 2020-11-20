package com.example.cash_you.ui.overview;

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
import com.example.cash_you.ui.categories.CategoriesViewModel;

public class OverviewFragment extends Fragment {

    private OverviewViewModel overviewViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        overviewViewModel =
                new ViewModelProvider(this).get(OverviewViewModel.class);
        View root = inflater.inflate(R.layout.fragment_overview, container, false);
        final TextView textView = root.findViewById(R.id.text_overview);
        overviewViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}