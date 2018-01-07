package com.example.hispeed.calculatoroil.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.View;
import android.widget.TextView;

import com.example.hispeed.calculatoroil.Models.DetailRecyclerView;
import com.example.hispeed.calculatoroil.R;

import java.util.ArrayList;

/**
 * Created by Hispeed on 20/8/2560.
 */

public class DetailVewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public TextView type_car;
    public TextView origin;
    public TextView destination;
    public TextView distance;
    public TextView duration;
    public TextView type_oil;
    public TextView spend_oil;
    public TextView money;
    public TextView str_date;
    public TextView average_baht;


    ArrayList<DetailRecyclerView> detailRecyclerViews = new ArrayList<>();

    ClickListener clickListener;
    GestureDetector gestureDetector;


    public DetailVewHolders(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.textNameCarDB);
        type_car = (TextView) itemView.findViewById(R.id.textTypeCarDB);
        origin = (TextView) itemView.findViewById(R.id.startTextLocationDB);
        destination = (TextView) itemView.findViewById(R.id.endTextLocationDB);
        distance = (TextView) itemView.findViewById(R.id.distanceTextOilDB);
        duration = (TextView) itemView.findViewById(R.id.durationTextOilDB);
        type_oil = (TextView) itemView.findViewById(R.id.textTypeOilDB);
        spend_oil = (TextView) itemView.findViewById(R.id.calOilDB);
        money = (TextView) itemView.findViewById(R.id.calMoneyDB);
        str_date = (TextView)itemView.findViewById(R.id.text_date);
        average_baht = (TextView)itemView.findViewById(R.id.average_DB);

    }


    @Override
    public void onClick(View v) {
        this.clickListener.onClick(v,getLayoutPosition());
    }


    public void removeAt(int position) {
        detailRecyclerViews.remove(position);

    }
}
