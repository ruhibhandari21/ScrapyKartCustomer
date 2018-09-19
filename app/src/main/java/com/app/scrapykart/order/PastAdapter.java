package com.app.scrapykart.order;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.scrapykart.R;
import com.app.scrapykart.vendor.RateCardSetter;
import com.app.scrapykart.vendor.SelectionActivity;

import java.util.ArrayList;

/**
 * Created by shadaf on 13/1/18.
 */

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.ViewHolder> {


    ArrayList<OrderSetter> orderSetters = null;
    PastFragment pastFragment;
    boolean selection = false;

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btCancel, btPositive;
        TextView tvItem, tvDate, tvCountValue, tvPriceValue, tvIdValue;

        public ViewHolder(View view) {
            super(view);
            /*tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvCountValue = (TextView) view.findViewById(R.id.tvCountValue);
            tvPriceValue = (TextView) view.findViewById(R.id.tvPriceValue);
            tvIdValue = (TextView) view.findViewById(R.id.tvIdValue);*/

            btCancel = (Button) view.findViewById(R.id.btCancel);
            btPositive = (Button) view.findViewById(R.id.btPositive);

        }
    }

    public PastAdapter(PastFragment pastFragment, ArrayList<OrderSetter> orderSetters) {

        this.pastFragment = pastFragment;
        this.orderSetters = orderSetters;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.past_order_item, parent, false);

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
        if(position % 2 == 0){
            holder.btPositive.setText("COMPLETE");
                holder.btPositive.setTextColor(Color.parseColor("#008975"));
        }else {
            holder.btPositive.setTextColor(Color.parseColor("#E53935"));
        }

        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(pastFragment.getContext(), "Under development",
                        Toast.LENGTH_SHORT).show();
            }
        });

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
        newFragment.show(pastFragment.getFragmentManager(), "dialog");
    }

}
