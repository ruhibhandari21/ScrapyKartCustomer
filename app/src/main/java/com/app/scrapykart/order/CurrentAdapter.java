package com.app.scrapykart.order;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.scrapykart.R;

import java.util.ArrayList;

/**
 * Created by shadaf on 13/1/18.
 */

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.ViewHolder> {


    ArrayList<OrderSetter> orderSetters = null;
    CurrentFragment currentFragment;
    boolean selection = false;

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btCancel, btPositive;
        TextView tvItem, tvDate, tvCountValue, tvPriceValue, tvIdValue;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
/*
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvCountValue = (TextView) view.findViewById(R.id.tvCountValue);
            tvPriceValue = (TextView) view.findViewById(R.id.tvPriceValue);
            tvIdValue = (TextView) view.findViewById(R.id.tvIdValue);
*/

            btCancel = (Button) view.findViewById(R.id.btCancel);
            btPositive = (Button) view.findViewById(R.id.btComplete);


        }
    }

    public CurrentAdapter(CurrentFragment currentFragment, ArrayList<OrderSetter> orderSetters) {

        this.currentFragment = currentFragment;
        this.orderSetters = orderSetters;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_order, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

/*
        OrderSetter setter = orderSetters.get(position);
        holder.tvItem.setText(setter.getVendorName());
        holder.tvDate.setText(setter.getOrderDate());
        holder.tvCountValue.setText(setter.getQuantity()+" Items");
        holder.tvPriceValue.setText("Rs. "+setter.getPrice());
        holder.tvIdValue.setText(setter.getOrderId());
        holder.ratingBar.setRating(3.0f);

        if(setter.getStatus() == OrderSetter.ORDER_CONFIRMED){
            holder.btPositive.setTextColor(Color.parseColor("#E53935"));
            holder.btPositive.setText("CANCELED");
        }else if(setter.getStatus() == OrderSetter.ORDER_PENDING){
            holder.btPositive.setTextColor(Color.parseColor("#E9915B"));
            holder.btPositive.setText("FEEDBACK");
        }

        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(currentFragment.getContext(), "Under development",
                        Toast.LENGTH_SHORT).show();
            }
        });
*/
        holder.btPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderSetters.size();
    }

    void showDialog() {
        CompleteOrderDialog newFragment = CompleteOrderDialog.newInstance(new OrderSetter());
        newFragment.show(currentFragment.getFragmentManager(), "dialog");
    }
}
