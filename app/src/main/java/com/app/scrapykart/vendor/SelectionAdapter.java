package com.app.scrapykart.vendor;

/**
 * Created by shadaf on 13/1/18.
 */

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.scrapykart.R;

import java.util.ArrayList;

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.ViewHolder> {


    ArrayList<RateCardSetter> cardSetters = null;
    SelectionActivity selectionActivity;
    boolean selection = false;

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btAdd, btMinus;
        TextView tvItem, tvUnitValue, tvUnit;

        public ViewHolder(View view) {
            super(view);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvUnitValue = (TextView) view.findViewById(R.id.tvUnitValue);
            tvUnit = (TextView) view.findViewById(R.id.tvUnit);
            btAdd = (Button) view.findViewById(R.id.btAdd);
            btMinus = (Button) view.findViewById(R.id.btMinus);
        }
    }

    public SelectionAdapter(SelectionActivity selectionActivity, ArrayList<RateCardSetter> cardSetters) {

        this.selectionActivity = selectionActivity;
        this.cardSetters = cardSetters;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rate_entry_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        RateCardSetter setter = cardSetters.get(position);
        holder.tvItem.setText(setter.getItemName());
        holder.tvUnit.setText(setter.getUnit()+" - "+setter.getPrice()+"Rs");
        holder.tvUnitValue.setText(setter.getQuantity()+"("+setter.getUnit()+")");

        holder.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RateCardSetter rateCardSetter = cardSetters.get(position);
                int qty = rateCardSetter.getQuantity();
                String unit = rateCardSetter.getUnit();

                if(qty < 100){
                    qty++;
                    rateCardSetter.setQuantity(qty);
                    holder.tvUnitValue.setText(qty+"("+unit+")");
                }else
                    Toast.makeText(selectionActivity, "Can not allow at the moment",
                            Toast.LENGTH_LONG).show();

                selectionActivity.updatePrice();
            }
        });

        holder.btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RateCardSetter rateCardSetter = cardSetters.get(position);
                int qty = rateCardSetter.getQuantity();
                String unit = rateCardSetter.getUnit();

                if(qty > 1){
                    qty--;
                    rateCardSetter.setQuantity(qty);
                    holder.tvUnitValue.setText(qty+"("+unit+")");
                }else
                    Toast.makeText(selectionActivity, "Value can not be zero",
                            Toast.LENGTH_LONG).show();
                selectionActivity.updatePrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardSetters.size();
    }

    public void setSelection(boolean selection){
        this.selection = selection;
    }

}
