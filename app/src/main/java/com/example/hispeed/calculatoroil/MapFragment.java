package com.example.hispeed.calculatoroil;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hispeed.calculatoroil.ConnectApi.DirectionFinderRetrofit;
import com.example.hispeed.calculatoroil.ConnectApi.RoutesValue;
import com.example.hispeed.calculatoroil.ConnectApi.DirectionFinderListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback, DirectionFinderListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int RESULT_OK = -1;

    public MapFragment() {
    }

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private EditText startAddress, endAddress;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    private String distanceStr, durationStr, distanceText, durationText, startLocation, endLocation;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private LatLng latLng;
    private Marker marker;
    private MarkerOptions markerOptions;

    private Button btncalculateOil, btnSearch, btnClearTextOrigin, btnClearTextDestination;

    private TextView textDistance, textDuration;

    private double locationLat, locationLng;

    private String locationAddress;

    private FragmentTransaction fragmentTransaction;
    private int checkedIndex = 1;

    private FloatingActionButton floatingCalculate;
    private ImageButton imageButton;

    private boolean checkStatusLocation = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("แผนที่");
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        bindView();
        clearTextOrigin();
        clearTextDestination();

        connectGoogleApi();

        mapFrag.getMapAsync(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWifi();
            }
        });

        floatingCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToCalculatorFragment();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = startAddress.getText().toString();
                String text2 = endAddress.getText().toString();

                startAddress.setText("");
                startAddress.setText(text2);

                endAddress.setText("");
                endAddress.setText(text1);
            }
        });

        locationGps();
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkLocationPermission()) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //Request location updates:
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.748530, 100.488814), 5));
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(googleApiClient);
        if (locationAvailability.isLocationAvailable()) {
            locationGps();
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        if (marker != null) {
            marker.remove();
        }
        locationLat = location.getLatitude();
        locationLng = location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                locationAddress = address;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        latLng = new LatLng(locationLat, locationLng);

        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(locationAddress);
        marker = mGoogleMap.addMarker(markerOptions);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getActivity(), "กรุณารอสักครู่",
                "กำลังค้นหาเส้นทาง...", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<RoutesValue> values) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        if (values.isEmpty()) {
            Toast.makeText(getActivity(), "ไม่สามารถค้นหาสถานที่นี้ได้", Toast.LENGTH_SHORT).show();
            textDuration.setText("0 mins");
            textDistance.setText("0 km");
            checkStatusLocation = false;
            return;
        }

        for (RoutesValue routesValue : values) {
            checkStatusLocation = true;

            distanceStr = String.valueOf(values.get(0).distance.value);
            durationStr = String.valueOf(values.get(0).duration.value);

            distanceText = values.get(0).distance.text;
            durationText = values.get(0).duration.text;

            zoomMap(routesValue.startLocation, routesValue.endLocation);

            textDuration.setText(durationText);
            textDistance.setText(distanceText);

            originMarkers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_origin))
                    .title(routesValue.startAddress)
                    .position(routesValue.startLocation)));
            destinationMarkers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_destination))
                    .title(routesValue.endAddress)
                    .position(routesValue.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < values.get(0).point.size(); i++)
                polylineOptions.add(values.get(0).point.get(i));

            polylinePaths.add(mGoogleMap.addPolyline(polylineOptions));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        //Request location updates:
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.navigation_drawer, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_location:
                final LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    isGPSEnabled();
                } else {
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationLat, locationLng), 15));
                }
                break;
            case R.id.btn_map_type:
                mapType();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindView() {
        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentmap);

        startAddress = (EditText) getActivity().findViewById(R.id.editstartlocation);
        endAddress = (EditText) getActivity().findViewById(R.id.editendlocation);

        btnSearch = (Button) getActivity().findViewById(R.id.btnsearch);
        btnClearTextOrigin = (Button) getActivity().findViewById(R.id.btncleartextori);
        btnClearTextDestination = (Button) getActivity().findViewById(R.id.btncleartextdes);

        floatingCalculate = (FloatingActionButton) getActivity().findViewById(R.id.btncalculateOil);

        imageButton = (ImageButton) getActivity().findViewById(R.id.btn_change_editext);

        textDuration = (TextView) getActivity().findViewById(R.id.texttime);
        textDistance = (TextView) getActivity().findViewById(R.id.textdistance);
    }

    private void connectGoogleApi() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void clearTextOrigin() {
        showButtonClearText(startAddress, btnClearTextOrigin);
    }

    private void clearTextDestination() {
        showButtonClearText(endAddress, btnClearTextDestination);
    }

    public void showButtonClearText(final EditText edtLocation, final Button btnClearText) {
        btnClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtLocation.setText("");
                btnClearText.setVisibility(View.INVISIBLE);
            }
        });

        edtLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    btnClearText.setVisibility(View.VISIBLE);

                } else {
                    btnClearText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void locationGps() {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000)
                .setFastestInterval(1000);
    }

    private void searchLocation() {
        startLocation = startAddress.getText().toString();
        endLocation = endAddress.getText().toString();

        if (startLocation.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณาใส่จุดเริ่มต้น", Toast.LENGTH_SHORT).show();
            return;
        } else if (endLocation.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณาใส่จุดปลายทาง", Toast.LENGTH_SHORT).show();
            return;
        } else if (startLocation.equals(endLocation)) {
            Toast.makeText(getActivity(), "กรุณาใส่สถานที่ที่แตกต่างกัน", Toast.LENGTH_SHORT).show();
            return;
        }

        new DirectionFinderRetrofit(this, startLocation, endLocation).connectLocation();
    }

    public void sendDataToCalculatorFragment() {
        Bundle bundle = new Bundle();
        if (checkStatusLocation) {
            CalculateOilFragment calculateOilFragment = new CalculateOilFragment();

            bundle.putString("distanceIntent", distanceStr);
            bundle.putString("durationIntent", durationStr);
            bundle.putString("distanceText", distanceText);
            bundle.putString("durationText", durationText);
            bundle.putString("startLocation", startLocation);
            bundle.putString("endLocation", endLocation);

            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_right, R.anim.exit_left, R.anim.enter_left, R.anim.exit_right);
            fragmentTransaction.replace(R.id.fragment_continer, calculateOilFragment).remove(mapFrag);
            //     fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            calculateOilFragment.setArguments(bundle);

        } else {
            Toast.makeText(getActivity(), "กรุณากรอกข้อมูลเส้นทาง", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile_net = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (!(wifi.isConnected()) && !(mobile_net.isConnected())) {
            Toast.makeText(getActivity(), "การเชื่อมต่อล้มเหลว", Toast.LENGTH_SHORT).show();
        } else {
            searchLocation();
            startAddress.onEditorAction(EditorInfo.IME_ACTION_DONE);
            endAddress.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("")
                        .setMessage("")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public void mapType() {
        String[] items = {"Hybrid", "Normal", "Satellite", "Terrain"};
        final int[] checkedItems = new int[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        builder.setTitle("ประเภทแผนที่")
                .setIcon(R.drawable.icon_app)
                .setSingleChoiceItems(items, checkedIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItems[0] = which;
                    }
                }).setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (checkedItems[0]) {
                    case 0:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        checkedIndex = 0;
                        break;
                    case 1:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        checkedIndex = 1;
                        break;
                    case 2:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        checkedIndex = 2;
                        break;
                    case 3:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        checkedIndex = 3;
                        break;
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void isGPSEnabled() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("คุณต้องการเปิด GPS หรือไม่?")
                .setCancelable(false)
                .setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void zoomMap(LatLng originPlace, LatLng destinationPlace) {
        try {
            int padding = 150;
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(originPlace);
            builder.include(destinationPlace);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), padding));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
