package com.example.lab8savkops;

import android.graphics.Color;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapHelper {
    private GoogleMap mMap;
    private LatLng userLocation;

    public MapHelper(GoogleMap map, LatLng location) {
        this.mMap = map;
        this.userLocation = location;
    }

    public void drawRoute(LatLng destination) {
        String waypoints = userLocation.latitude + "," + userLocation.longitude + "|" +
                destination.latitude + "," + destination.longitude;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.geoapify.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GeoapifyService service = retrofit.create(GeoapifyService.class);
        Call<GeoapifyResponse> call = service.getRoute("e1868e477f274719927d712ae79e4cca", waypoints);

        call.enqueue(new Callback<GeoapifyResponse>() {
            @Override
            public void onResponse(Call<GeoapifyResponse> call, Response<GeoapifyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PolylineOptions polylineOptions = new PolylineOptions()
                            .color(Color.BLUE)
                            .width(10);
                    for (LatLng point : response.body().getRoutePoints()) {
                        polylineOptions.add(point);
                    }
                    mMap.addPolyline(polylineOptions);
                }
            }

            @Override
            public void onFailure(Call<GeoapifyResponse> call, Throwable t) {
                // Обработка ошибок
            }
        });
    }
}