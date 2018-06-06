package com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.legs;

import com.google.gson.annotations.SerializedName;

public class OverviewPolyline {

    @SerializedName("points")
    private String points;

    public String getPoints() {
        return points;
    }
}
