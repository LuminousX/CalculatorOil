package com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.legs;

import com.google.gson.annotations.SerializedName;

public class EndLocation {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
