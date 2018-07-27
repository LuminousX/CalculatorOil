package com.example.hispeed.calculatoroil.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hispeed.calculatoroil.R;
import com.example.hispeed.calculatoroil.ViewHolders.DetailVewHolders;

import java.util.ArrayList;

/**
 * Created by Hispeed on 20/8/2560.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<DetailVewHolders> {

    Context context;
    ArrayList<DetailRecyclerView> detailRecyclerViews = new ArrayList<>();
    LayoutInflater inflater;
    View view;
    DetailVewHolders detailVewHolders;

    public RecyclerViewAdapter(Context context, ArrayList<DetailRecyclerView> detailRecyclerViews) {
        this.context = context;
        this.detailRecyclerViews = detailRecyclerViews;
        notifyItemRangeChanged(0, detailRecyclerViews.size());
        inflater = LayoutInflater.from(context);

    }


    @Override
    public DetailVewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.listviewhistory, parent, false);
        detailVewHolders = new DetailVewHolders(view);
        return detailVewHolders;
    }

    @Override
    public void onBindViewHolder(final DetailVewHolders holder, int position) {
//        final DetailRecyclerView detailRecyclerView = detailRecyclerViews.get(position);

        holder.name.setText(detailRecyclerViews.get(position).getName());
        holder.type_car.setText(detailRecyclerViews.get(position).getType_car());
        holder.origin.setText(detailRecyclerViews.get(position).getOrigin());
        holder.destination.setText(detailRecyclerViews.get(position).getDestination());
        holder.distance.setText(detailRecyclerViews.get(position).getDistance());
        holder.duration.setText(detailRecyclerViews.get(position).getDuration());
        holder.type_oil.setText(detailRecyclerViews.get(position).getType_oil());
        holder.spend_oil.setText(detailRecyclerViews.get(position).getSpend_oil());
        holder.money.setText(detailRecyclerViews.get(position).getMoney());
        holder.str_date.setText(detailRecyclerViews.get(position).getStr_date());
        holder.average_baht.setText(detailRecyclerViews.get(position).getAverage_baht());

    }

    @Override
    public int getItemCount() {
        return detailRecyclerViews.size();
    }
}
