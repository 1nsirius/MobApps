package com.example.lab8savkops;

import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerHelper {
    private GoogleMap mMap;
    private LatLng userLocation;

    public MarkerHelper(GoogleMap map, LatLng location) {
        this.mMap = map;
        this.userLocation = location;
    }

    public void onAddMarkerClick(View view) {
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Посещенное место"));
    }
}