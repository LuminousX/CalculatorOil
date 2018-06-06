package com.example.hispeed.calculatoroil.ConnectApi;

import android.util.Log;

import com.example.hispeed.calculatoroil.ConnectApi.addressLocation.AddressLocation;
import com.example.hispeed.calculatoroil.Models.DirectionFinderListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectionFinderRetrofit {
    private String origin;
    private String destination;
    private DirectionFinderListener listener;
    List<RoutesValue> routesValues = new ArrayList<>();
    RoutesValue value = new RoutesValue();

    private static final String DIRECTION_URL_API = "https://maps.googleapis.com";
    private static final String severkey = "AIzaSyC5LguwgTuUI1cVKd5QKOIae-6u1Pr3J48";

    public DirectionFinderRetrofit(DirectionFinderListener listener, String origin, String destination) {
        this.listener = listener;
        this.origin = origin;
        this.destination = destination;
    }

    public void connectLocation() {
        listener.onDirectionFinderStart();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DIRECTION_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<AddressLocation> call = retrofitApi.getLocation(origin, destination, severkey);
        call.enqueue(new Callback<AddressLocation>() {
            @Override
            public void onResponse(Call<AddressLocation> call, Response<AddressLocation> response) {
                if (!response.body().getStatus().equals("OK")) {
                    routesValues.clear();
                    listener.onDirectionFinderSuccess(routesValues);
                    return;
                }

                if (response.isSuccessful()) {
                    String distanceText = response.body().getRoutes().get(0).getLegs().get(0).getDistance().getText();
                    int distanceValue = response.body().getRoutes().get(0).getLegs().get(0).getDistance().getValue();

                    String durationText = response.body().getRoutes().get(0).getLegs().get(0).getDuration().getText();
                    int durationValue = response.body().getRoutes().get(0).getLegs().get(0).getDuration().getValue();

                    double endLocationLat = response.body().getRoutes().get(0).getLegs().get(0).getEnd_location().getLat();
                    double endLocationLng = response.body().getRoutes().get(0).getLegs().get(0).getEnd_location().getLng();

                    double startLocationLat = response.body().getRoutes().get(0).getLegs().get(0).getStart_location().getLat();
                    double startLocationLng = response.body().getRoutes().get(0).getLegs().get(0).getStart_location().getLng();

                    String startAddress = response.body().getRoutes().get(0).getLegs().get(0).getStart_address();
                    String endAddress = response.body().getRoutes().get(0).getLegs().get(0).getEnd_address();

                    String polyline = response.body().getRoutes().get(0).getOverview_polyline().getPoints();

                    value.distance = new Distance(distanceText, distanceValue);
                    value.duration = new Duration(durationText, durationValue);

                    value.startAddress = startAddress;
                    value.endAddress = endAddress;

                    value.startLocation = new LatLng(startLocationLat, startLocationLng);
                    value.endLocation = new LatLng(endLocationLat, endLocationLng);

                    value.point = decodePolyLine(polyline);

                    routesValues.add(value);

                    listener.onDirectionFinderSuccess(routesValues);
                } else {

                }
            }

            @Override
            public void onFailure(Call<AddressLocation> call, Throwable t) {

            }
        });
    }

    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }
        return decoded;
    }
}
