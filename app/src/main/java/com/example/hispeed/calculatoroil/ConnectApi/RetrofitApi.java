package com.example.hispeed.calculatoroil.ConnectApi;

import com.example.hispeed.calculatoroil.ConnectApi.addressLocation.AddressLocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("/maps/api/directions/json?")
    Call<AddressLocation> getLocation(@Query("origin") String origin,
                                      @Query("destination") String destination,
                                      @Query("key") String key);
}
