package com.app.scrapykart.vendor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Selection;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.scrapykart.R;
import com.app.scrapykart.order.OrderActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<RateCardSetter> cardSetters;
    Button btPlaceOrder;
    TextView tvPrice;
    private RecyclerView recyclerCard;
    private SelectionAdapter selectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initUI();
        initListener();
        initiateSetter();
        setupRecycler();
    }

    private void initListener() {
        btPlaceOrder.setOnClickListener(this);
    }

    private void initUI() {
        recyclerCard = (RecyclerView) findViewById(R.id.recyclerRateCard);

        btPlaceOrder = (Button) findViewById(R.id.btPlaceOrder);

        tvPrice = (TextView) findViewById(R.id.tvPrice);
    }

    private void setupRecycler() {

        selectionAdapter = new SelectionAdapter(this, cardSetters);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerCard.setLayoutManager(mLayoutManager);
        recyclerCard.setItemAnimator(new DefaultItemAnimator());
        recyclerCard.setAdapter(selectionAdapter);
        updatePrice();

    }

    private void initiateSetter() {

        cardSetters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cardSetters.add(new RateCardSetter("Type" + i, "Books, papers etc",
                    "kg", (i + 1) + "0"));
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btPlaceOrder:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font color='#000000'>Thank You</font>"));
                builder.setMessage(Html.fromHtml("<font color='#000000'>Your request has been sent to the vendor." +
                        " You will soon get response from the vendor.</font>"));
                builder.setPositiveButton("CHECK ORDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        startActivity(new Intent(SelectionActivity.this, OrderActivity.class));
                        Toast.makeText(SelectionActivity.this, "Navigate to order screen",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                break;

        }

    }


    public void updatePrice(){

        double price = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        for(RateCardSetter setter : cardSetters){
            price = price + (Double.parseDouble(setter.getPrice()) * setter.getQuantity());
        }

        tvPrice.setText("Rs "+ decimalFormat.format(price));
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
