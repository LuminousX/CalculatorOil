package com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.legs;

import com.google.gson.annotations.SerializedName;

public class Duration {

    @SerializedName("text")
    private String text;

    @SerializedName("value")
    private int value;

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
