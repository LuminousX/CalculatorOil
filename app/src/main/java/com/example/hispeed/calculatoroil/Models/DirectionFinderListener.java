package com.example.hispeed.calculatoroil.Models;

import com.example.hispeed.calculatoroil.Models.Route;

import java.util.List;

/**
 * Created by Hispeed on 11/6/2560.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
