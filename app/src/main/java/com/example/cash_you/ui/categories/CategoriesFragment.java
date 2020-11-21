package com.example.cash_you.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cash_you.R;
import com.example.cash_you.models.Category;
import com.github.mikephil.charting.charts.PieChart;

public class CategoriesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_categories, container, false);

        // Create default categories - Food, Eating out, Fare, Purchases
        Category foodCategory = new Category("Food");
        final TextView foodName = (TextView) view.findViewById(R.id.foodName);
        final TextView foodBalance = (TextView) view.findViewById(R.id.foodBalance);
        foodName.setText(foodCategory.GetName());
        foodBalance.setText(foodCategory.GetCurrentBalance());

        Category eatingOutCategory = new Category("Eating out");
        final TextView eatingOutName = (TextView) view.findViewById(R.id.eatingOutName);
        final TextView eatingOutBalance = (TextView) view.findViewById(R.id.eatingOutBalance);
        eatingOutName.setText(eatingOutCategory.GetName());
        eatingOutBalance.setText(eatingOutCategory.GetCurrentBalance());

        Category fareCategory = new Category("Fare");
        final TextView fareName = (TextView) view.findViewById(R.id.fareName);
        final TextView fareBalance = (TextView) view.findViewById(R.id.fareBalance);
        fareName.setText(fareCategory.GetName());
        fareBalance.setText(fareCategory.GetCurrentBalance());

        Category purchasesCategory = new Category("Purchases");
        final TextView purchasesName = (TextView) view.findViewById(R.id.purchasesName);
        final TextView purchasesBalance = (TextView) view.findViewById(R.id.purchasesBalance);
        purchasesName.setText(purchasesCategory.GetName());
        purchasesBalance.setText(purchasesCategory.GetCurrentBalance());

        PieChart chart = (PieChart) view.findViewById(R.id.outcome_chart);

        Chart outcomeChart = new Chart(chart);
        outcomeChart.addData(50, ContextCompat.getColor(getActivity(), R.color.foodColor));
        outcomeChart.addData(40, ContextCompat.getColor(getActivity(), R.color.eatingOutColor));
        outcomeChart.addData(30, ContextCompat.getColor(getActivity(), R.color.fareColor));
        outcomeChart.addData(20, ContextCompat.getColor(getActivity(), R.color.purchasesColor));

        return view;
    }
}