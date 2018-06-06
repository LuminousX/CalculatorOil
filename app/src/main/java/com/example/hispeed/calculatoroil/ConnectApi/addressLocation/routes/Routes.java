package com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes;

import com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.legs.Legs;
import com.example.hispeed.calculatoroil.ConnectApi.addressLocation.routes.legs.OverviewPolyline;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Routes {

    @SerializedName("legs")
    private List<Legs> legs;

    @SerializedName("overview_polyline")
    private OverviewPolyline overview_polyline;

    public OverviewPolyline getOverview_polyline() {
        return overview_polyline;
    }

    public List<Legs> getLegs() {
        return legs;
    }
}
