package com.app.scrapykart.order;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.scrapykart.R;
import com.app.scrapykart.vendor.RateAdapter;
import com.app.scrapykart.vendor.RateCardSetter;

import java.util.ArrayList;

public class CurrentFragment extends Fragment {

    RecyclerView recyclerOrder;
    CurrentAdapter currentAdapter;
    View view;
    ArrayList<OrderSetter> orderSetters;

    public CurrentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_current, container, false);
        initUI();
        initiateSetter();
        setupRecycler();
        return view;
    }

    private void initUI() {
        recyclerOrder = (RecyclerView) view.findViewById(R.id.recyclerOrder);
    }

    private void setupRecycler() {

        currentAdapter = new CurrentAdapter(this, orderSetters);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerOrder.setLayoutManager(mLayoutManager);
        recyclerOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerOrder.setAdapter(currentAdapter);

    }

    private void initiateSetter() {

        orderSetters = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            OrderSetter setter = new OrderSetter();
            setter.setVendorName("Scrapper name "+i);
            setter.setOrderDate("10th Jun 17, 2.15PM");
            setter.setOrderId("SCRP8953"+i+"12"+i);
            setter.setPrice(i*10);
            setter.setQuantity(i*3);
            if(i == 0 || i == 2 || i == 4){
                setter.setStatus(OrderSetter.ORDER_PENDING);
            }else{
                setter.setStatus(OrderSetter.ORDER_CONFIRMED);
            }

            orderSetters.add(setter);
        }

    }

}
