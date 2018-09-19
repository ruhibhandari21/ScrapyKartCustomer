package com.app.scrapykart.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;


import com.app.scrapykart.R;

import java.io.Serializable;

/**
 * Created by Admin on 2/16/2018.
 */

public class CompleteOrderDialog extends DialogFragment implements View.OnClickListener {

    OrderSetter orderSetter;
    View rootView;

    public static CompleteOrderDialog newInstance(OrderSetter setter) {
        CompleteOrderDialog fragment = new CompleteOrderDialog();
        Bundle args = new Bundle();
//        args.putSerializable("setter", (Serializable) setter);
        fragment.setArguments(args);
        return fragment;
    }

    public CompleteOrderDialog(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_complete_order, container, false);

        initializeVariable();
        initUI(rootView);
        initListener(rootView);

        return rootView;
    }

    private void initUI(View rootView) {
    }

    private void initListener(View rootView) {
        rootView.findViewById(R.id.btCancel).setOnClickListener(this);
        rootView.findViewById(R.id.btAction).setOnClickListener(this);
    }

    private EditText getEdit(int id){
        return (EditText) rootView.findViewById(id);
    }

    private void initializeVariable() {

        Bundle bundle = getArguments();
    }

    private void setData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCancel:
                dismiss();
                break;

            case R.id.btAction: dismiss();
                break;
        }
    }
}
