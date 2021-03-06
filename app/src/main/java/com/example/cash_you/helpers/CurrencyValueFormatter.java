package com.example.cash_you.helpers;

import com.example.cash_you.helpers.Helper;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;


public class CurrencyValueFormatter extends DefaultValueFormatter implements ValueFormatter {
    public CurrencyValueFormatter(int digits) {
        super(digits);
    }
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return Helper.currencyFormat.format(value);
    }
}
