package com.example.hispeed.calculatoroil.ConnectApi.addressLocation;

import com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.Routes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressLocation {

    @SerializedName("routes")
    private List<Routes> routes;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public List<Routes> getRoutes() {
        return routes;
    }
}
