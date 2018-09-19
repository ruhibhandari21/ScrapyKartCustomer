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

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {


    ArrayList<OrderSetter> orderSetters = null;
    PendingFragment pendingFragment;
    boolean selection = false;

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btCancel, btPositive;
        TextView tvItem, tvDate, tvCountValue, tvPriceValue, tvIdValue;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
  /*          tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvCountValue = (TextView) view.findViewById(R.id.tvCountValue);
            tvPriceValue = (TextView) view.findViewById(R.id.tvPriceValue);
            tvIdValue = (TextView) view.findViewById(R.id.tvIdValue);
*/
            btCancel = (Button) view.findViewById(R.id.btCancel);
            btPositive = (Button) view.findViewById(R.id.btPositive);


        }
    }

    public PendingAdapter(PendingFragment pendingFragment, ArrayList<OrderSetter> orderSetters) {

        this.pendingFragment = pendingFragment;
        this.orderSetters = orderSetters;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_order_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        /*OrderSetter setter = orderSetters.get(position);
        holder.tvItem.setText(setter.getVendorName());
        holder.tvDate.setText(setter.getOrderDate());
        holder.tvCountValue.setText(setter.getQuantity()+" Items");
        holder.tvPriceValue.setText("Rs. "+setter.getPrice());
        holder.tvIdValue.setText(setter.getOrderId());
*/

        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(pendingFragment.getContext(), "Under development",
                        Toast.LENGTH_SHORT).show();
            }
        });

        holder.btPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(pendingFragment.getContext(), "Under development",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderSetters.size();
    }


}
