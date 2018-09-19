package com.app.scrapykart.vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.scrapykart.R;

import java.util.ArrayList;

public class RateCardActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<RateCardSetter> cardSetters;
    Button btSelectItem, btConfirmSelection, btCancel;
    RelativeLayout relativeSelection;
    TextView tvCardTitle;
    private RecyclerView recyclerCard;
    private RateAdapter rateAdapter;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initUI();
        initListener();
        initiateSetter();
        setupRecycler();

    }

    private void initListener() {
        btSelectItem.setOnClickListener(this);
        btConfirmSelection.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initUI() {
        recyclerCard = (RecyclerView) findViewById(R.id.recyclerRateCard);

        btSelectItem = (Button) findViewById(R.id.btSelectItem);
        btConfirmSelection = (Button) findViewById(R.id.btConfirmSelection);
        btCancel = (Button) findViewById(R.id.btCancel);

        relativeSelection = (RelativeLayout) findViewById(R.id.relativeSelection);
        tvCardTitle = (TextView) findViewById(R.id.tvCardTitle);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(3.0f);
    }

    private void setupRecycler() {

        rateAdapter = new RateAdapter(this, cardSetters);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerCard.setLayoutManager(mLayoutManager);
        recyclerCard.setItemAnimator(new DefaultItemAnimator());
        recyclerCard.setAdapter(rateAdapter);

    }

    private void initiateSetter() {

        cardSetters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cardSetters.add(new RateCardSetter("Type" + i, "Books, papers etc",
                    "unit" + i, (i + 1) + "0"));
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btSelectItem:
                relativeSelection.setVisibility(View.VISIBLE);
                btSelectItem.setVisibility(View.GONE);
                tvCardTitle.setText("Select item from the list");

                rateAdapter.setSelection(true);
                rateAdapter.notifyDataSetChanged();

                break;

            case R.id.btConfirmSelection:

                checkSelection();

                break;

            case R.id.btCancel:

                relativeSelection.setVisibility(View.GONE);
                tvCardTitle.setText("Rate Card");
                btSelectItem.setVisibility(View.VISIBLE);

                resetSetter();
                rateAdapter.setSelection(false);
                rateAdapter.notifyDataSetChanged();

                break;
        }

    }

    private void checkSelection() {

        boolean selected = false;
        for (RateCardSetter setter : cardSetters) {
            if (setter.isSelected())
                selected = true;
        }

        if(selected){
            Intent intent = new Intent(RateCardActivity.this, SelectionActivity.class);
            startActivity(intent);
        }else Toast.makeText(this, "Please select atleast one item from the list",
                Toast.LENGTH_LONG).show();
    }

    private void resetSetter() {

        for (RateCardSetter setter : cardSetters) {
            setter.setSelected(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
