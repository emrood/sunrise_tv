package com.wyzdev.sunrisetv.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wyzdev.sunrisetv.R;
import com.wyzdev.sunrisetv.models.TruckStation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TruckStationAdapter extends RecyclerView.Adapter<TruckStationAdapter.CardViewHolder> {
    private List<TruckStation> dataList;

    NumberFormat format;
    DecimalFormat decimalFormat;

    public TruckStationAdapter(List<TruckStation> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_price, parent, false);
        return new CardViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        TruckStation item = dataList.get(position);

        try{

            if(item.truck != null){
                holder.title.setText(item.truck.immatriculation);
                holder.description.setText(item.truck.name);

                if(item.truck.status == 0){
                    //prix fixe
                    holder.price.setText(new DecimalFormat("0.00").format(item.price) + " USD");
                }else{
                    holder.price.setText(new DecimalFormat("0.00").format(item.price) + " USD / Lbs");
                }
            }
        }catch (Exception e){

        }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        TextView description;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_product_name);
            price = itemView.findViewById(R.id.tv_price);
            description = itemView.findViewById(R.id.description);
        }
    }
}
