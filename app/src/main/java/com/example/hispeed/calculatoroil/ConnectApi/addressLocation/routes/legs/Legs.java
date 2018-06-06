package com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.legs;

import com.google.gson.annotations.SerializedName;

public class Legs {

    @SerializedName("distance")
    private Distance distance;

    @SerializedName("duration")
    private Duration duration;

    @SerializedName("end_location")
    private EndLocation end_location;

    @SerializedName("start_location")
    private StartLocation start_location;

    @SerializedName("end_address")
    private String end_address;

    @SerializedName("start_address")
    private String start_address;

    public String getEnd_address() {
        return end_address;
    }

    public String getStart_address() {
        return start_address;
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public EndLocation getEnd_location() {
        return end_location;
    }

    public StartLocation getStart_location() {
        return start_location;
    }
}
