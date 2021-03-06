package com.example.cash_you.helpers;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.PermissionChecker;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.example.cash_you.App;
import com.example.cash_you.R;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class Helper
{
    //Sort and filter methods
    public enum SORT_METHODS // Ascending means smaller on top, Descending means smaller on bottom
    {
        DATE_DSC,
        DATE_ASC,

        COST_DSC,
        COST_ASC,

        CATEGORY_DSC,
        CATEGORY_ASC,

        SOURCE_DSC,
        SOURCE_ASC,

        PAIDBY_DSC,
        PAIDBY_ASC
    }
    public enum FILTER_METHODS
    {
        CATEGORY,
        SOURCE,
        PAIDBY,
        SPLITWITH,
        PAIDBACK
    }

    // Sort and Filter methods string titles
    public static HashMap<FILTER_METHODS, Integer> filterTitles;
    static {
        filterTitles = new HashMap<>();

        filterTitles.put(FILTER_METHODS.CATEGORY, R.string.category);
        filterTitles.put(FILTER_METHODS.SOURCE, R.string.source);
        filterTitles.put(FILTER_METHODS.PAIDBY, R.string.paidby);
        filterTitles.put(FILTER_METHODS.SPLITWITH, R.string.splitwith);
        filterTitles.put(FILTER_METHODS.PAIDBACK, R.string.paidback);
    }

    // Sort and filter
    public static SORT_METHODS SortDirectionFlip(int method){
        return SORT_METHODS.values()[method + (method % 2 == 0 ? 1 : -1)];
    }
    public static SORT_METHODS SortSelect(int current, int target){
        if ( current == target || current+1 == target ){
            return Helper.SortDirectionFlip(current);
        } else {
            return Helper.SORT_METHODS.values()[target];
        }
    }

    public static String FilterString(Context context, HashMap<FILTER_METHODS, String> filters){
        String ret = context.getString(R.string.filter_by) + " ";
        for (HashMap.Entry<FILTER_METHODS, String> entry : filters.entrySet()){
            ret += context.getString(filterTitles.get(entry.getKey())) + ":" + entry.getValue() + ", ";
        }
        if (ret.length() > 0){ ret = ret.substring(0, ret.length()-2); }
        return ret;
    }

    // Formatters
    public static DecimalFormat decimalFormat = new DecimalFormat("#.###");
    public static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(); //new DecimalFormat("¤#.###");


    // Universal print
    public static void Print(Context c, String msg){ if (c!=null && isDebugMode(c)) { Toast.makeText(c, msg, Toast.LENGTH_SHORT).show(); } }
    public static void PrintLong(Context c, String msg){ if (c!=null && isDebugMode(c)) { Toast.makeText(c, msg, Toast.LENGTH_LONG).show(); } }
    public static void PrintUser(Context c, String msg){ if (c!=null){ Toast.makeText(c, msg, Toast.LENGTH_SHORT).show(); } }
    public static void PrintUserLong(Context c, String msg){ if (c!=null){ Toast.makeText(c, msg, Toast.LENGTH_LONG).show(); } }

    public static void Log(Context c, String cat, String msg){ if (c!=null && isDebugMode(c)) { Log.e(cat, msg); } }


    //Dialog fragment manager
    public static void OpenDialogFragment(Activity caller, DialogFragment fragment, boolean openAsDialogFragment){
        if (fragment != null) {
            if (openAsDialogFragment) { // The device is using a large layout, so show the fragment as a dialog
                FragmentTransaction ft = caller.getFragmentManager().beginTransaction();
                Fragment prev = caller.getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                fragment.show(caller.getFragmentManager(), "dialog");
                //FragmentTransaction ft = fragmentManager.beginTransaction();
                //ft.add(fragment, null);
                //ft.commitAllowingStateLoss();
            }
            else { // The device is smaller, so show the fragment fullscreen
                FragmentTransaction transaction = caller.getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, fragment).addToBackStack(null).commit();
            }
        }
    }


    //Getting string resources from static contexts
    //public static String getString(int resourceID){ return App.GetResources().getString(resourceID); }
    public static int getColor(int resourceID){ return App.GetResources().getColor(resourceID); }
    public static Drawable getDrawable(int resourceID) { return App.GetResources().getDrawable(resourceID); }


    //Hide Soft Keyboard
    public static void hideSoftKeyboard(Activity act, View v)
    {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    //Show Soft Keyboard
    public static void showSoftKeyboard(Activity act, View v)
    {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    //Color parsing
    public static int ColorFromString(String str){
        //Hash the string
        str = String.format("%X", str.hashCode());
        //Minimum string length = 6
        while (str.length() < 6) { str = '0' + str; }

        //Convert Hex string into int
        int coli = (int)Long.parseLong(str, 16);
        int r = (coli >> 16) & 0xFF;
        int g = (coli >> 8) & 0xFF;
        int b = (coli) & 0xFF;

        return Color.argb(255,r,g,b);
    }


    // Date parsing
    public static LocalDate ConvertDateFromString(Context context, String str){
        if (str != null && str.length() != 0) {
            try {
                DateTimeFormatter dtf = DateTimeFormat.forPattern(context.getString(R.string.date_format_saving));
                return dtf.parseLocalDate(str);
            }
            catch (IllegalArgumentException ex) {
                //ex.printStackTrace();
            }
        }
        return null;
    }


    //Permissions
    public static boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (PermissionChecker.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //Log.v("PERMISSIONS","External Write Storage permission is granted");
                return true;
            } else {
                //Log.v("PERMISSIONS", "External Write Storage permission is not granted");
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v("PERMISSIONS","Permission is granted by default");
            return true;
        }
    }

    //DEBUG
    public static boolean isDebugMode(Context ac){
        return (0 != (ac.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

}
