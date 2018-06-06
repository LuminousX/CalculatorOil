package com.example.hispeed.calculatoroil.Models;

import com.example.hispeed.calculatoroil.ConnectApi.RoutesValue;


import java.util.List;

/**
 * Created by Hispeed on 11/6/2560.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();

    void onDirectionFinderSuccess(List<RoutesValue> values);
}
