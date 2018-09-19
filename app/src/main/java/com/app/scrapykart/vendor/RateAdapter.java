package com.app.scrapykart.vendor;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.scrapykart.R;

import java.util.ArrayList;

/**
 * Created by shadaf on 12/1/18.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {


    ArrayList<RateCardSetter> cardSetters = null;
    RateCardActivity rateCardActivity;
    boolean selection = false;

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView rateCardView;
        TextView tvItemName, tvItemDetails, tvUnit;

        public ViewHolder(View view) {
            super(view);
            tvItemName = (TextView) view.findViewById(R.id.tvItem);
            tvItemDetails = (TextView) view.findViewById(R.id.tvItemDetails);
            tvUnit = (TextView) view.findViewById(R.id.tvUnit);
            rateCardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

    public RateAdapter(RateCardActivity rateCardActivity, ArrayList<RateCardSetter> cardSetters) {

        this.rateCardActivity = rateCardActivity;
        this.cardSetters = cardSetters;

    }

    @Override
    public RateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rate_card_item, parent, false);

        return new RateAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RateAdapter.ViewHolder holder, final int position) {

        RateCardSetter setter = cardSetters.get(position);
        holder.tvItemName.setText(setter.getItemName());
        holder.tvItemDetails.setText(setter.getOtherDetails());
        holder.tvUnit.setText(setter.getUnit()+" - "+setter.getPrice()+"Rs");

        if(!selection){
            holder.rateCardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.rateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CardView cardView = (CardView) view;
                RateCardSetter rateCardSetter = cardSetters.get(position);
                if(selection){
                    if(rateCardSetter.isSelected() ){
                        rateCardSetter.setSelected(false);
                        cardView.setCardBackgroundColor(ContextCompat.getColor(rateCardActivity, R.color.colorWhite));
                    }else{
                        rateCardSetter.setSelected(true);
                        cardView.setCardBackgroundColor(ContextCompat.getColor(rateCardActivity, R.color.windowBackground));
                    }
                }

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