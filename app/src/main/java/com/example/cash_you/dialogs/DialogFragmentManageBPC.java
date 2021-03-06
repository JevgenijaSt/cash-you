package com.example.cash_you.dialogs;


import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cash_you.interfaces.ParentCallBack;
import com.example.cash_you.R;

public class DialogFragmentManageBPC extends DialogFragment
{
    AppCompatActivity _parent;

    String _title;
    String _subtitle;

    String _editBtnTxt;
    String _selectBtnTxt;
    String _deleteBtnTxt;

    String _objInput;
    ParentCallBack _editFunc;
    ParentCallBack _selectFunc;
    ParentCallBack _deleteFunc;

    TextView textView_title;
    TextView textView_subtitle;

    Button button_edit;
    Button button_select;
    Button button_delete;

    public static DialogFragmentManageBPC newInstance(AppCompatActivity parent,
                                               String title, String subtitle, String objInput,
                                               ParentCallBack editFunc, ParentCallBack selectFunc, ParentCallBack deleteFunc)
    { return newInstance(parent, title, subtitle, objInput, null, null, null, editFunc, selectFunc, deleteFunc); }
    public static DialogFragmentManageBPC newInstance(AppCompatActivity parent,
                                               String title, String subtitle, String objInput,
                                               String editTxt, String selectTxt, String deleteTxt,
                                               ParentCallBack editFunc, ParentCallBack selectFunc, ParentCallBack deleteFunc) {
        DialogFragmentManageBPC fg = new DialogFragmentManageBPC();

        fg._parent = parent;
        fg._title = title;
        fg._subtitle = subtitle;

        fg._editBtnTxt = editTxt;
        fg._selectBtnTxt = selectTxt;
        fg._deleteBtnTxt = deleteTxt;

        fg._objInput = objInput;

        fg._editFunc = editFunc;
        fg._selectFunc = selectFunc;
        fg._deleteFunc = deleteFunc;

        //Bundle args = new Bundle();
        //args.putSerializable("profile", current);
        //fg.setArguments(args);

        return fg;
    }

    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_managebpc, container, false);
        view.setBackgroundColor(Color.WHITE);

        textView_title = (TextView) view.findViewById(R.id.textView_dialogppc_title);
        textView_subtitle = (TextView) view.findViewById(R.id.textView_dialogppc_subtitle);

        button_edit = (Button) view.findViewById(R.id.button_dialogppc_edit);
        button_select = (Button) view.findViewById(R.id.button_dialogppc_select);
        button_delete = (Button) view.findViewById(R.id.button_dialogppc_delete);

        // Button texts
        if (_editBtnTxt != null){ button_edit.setText(_editBtnTxt); }
        if (_selectBtnTxt != null){ button_select.setText(_selectBtnTxt); }
        if (_deleteBtnTxt != null){ button_delete.setText(_deleteBtnTxt); }

        //Visibility based on callback function null-ality
        if (_editFunc == null) { button_edit.setVisibility(View.GONE); }
        if (_selectFunc == null) { button_select.setVisibility(View.GONE); }
        if (_deleteFunc == null) { button_delete.setVisibility(View.GONE); }

        //Set title
        textView_title.setText(_title);

        //Set subtitle
        textView_subtitle.setText(_subtitle);
        if (_subtitle!=null && _subtitle.equals("")) { textView_subtitle.setVisibility(View.GONE); }

        //Edit
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_editFunc != null) { _editFunc.call(_objInput, DialogFragmentManageBPC.this); }
            }
        });

        //Select
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_selectFunc != null) { _selectFunc.call(_objInput, DialogFragmentManageBPC.this); }
            }
        });

        //Delete
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_deleteFunc != null) { _deleteFunc.call(_objInput, DialogFragmentManageBPC.this); }
            }
        });

        //Inflate the layout to use as dialog or embedded fragment
        return view;
    }



    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCanceledOnTouchOutside(false); //Disable closing dialog by clicking outside of it
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //dialog.getWindow().setBackgroundDrawable(null);
        }
    }
}