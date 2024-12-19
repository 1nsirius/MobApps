package com.example.lab8savkops;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoapifyService {
    @GET("v1/routing")
    Call<GeoapifyResponse> getRoute(
            @Query("apiKey") String apiKey,
            @Query("waypoints") String waypoints
    );
}