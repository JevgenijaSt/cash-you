package com.example.cash_you.ui.categories;

import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class Chart {
    private PieChart chart;
    private ArrayList<PieEntry> entries = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();

    Chart(PieChart chart) {
        this.chart = chart;
        initChart();
    }

    private void initChart() {
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5,10,5,5);

        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(true);

        chart.setTransparentCircleRadius(61f);

        chart.animateY(1000, Easing.EaseInOutCubic);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
    }

    private void setData() {
        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(0f);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    public void addData(int balance, int color) {
        entries.add(new PieEntry(balance, ""));
        colors.add(color);

        setData();
    }

}
