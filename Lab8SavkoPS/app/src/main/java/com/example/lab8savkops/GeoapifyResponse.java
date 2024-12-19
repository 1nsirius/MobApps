package com.example.lab8savkops;

import com.google.android.gms.maps.model.LatLng;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GeoapifyResponse {
    private List<LatLng> routes;

    public GeoapifyResponse() {
        this.routes = new ArrayList<>();
    }

    public List<LatLng> getRoutePoints() {
        return routes;
    }

    public void fetchRoute() {
        try {
            URL url = new URL("https://api.geoapify.com/v1/routing?waypoints=50.96209827745463%2C4.414458883409225%7C50.429137079078345%2C5.00088081232559&mode=drive&apiKey=e1868e477f274719927d712ae79e4cca");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Accept", "application/json");

            int responseCode = http.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                parseResponse(response.toString());
            } else {
                System.out.println(responseCode + " " + http.getResponseMessage());
            }
            http.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray features = jsonObject.getJSONArray("features");
            if (features.length() > 0) {
                JSONObject geometry = features.getJSONObject(0).getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                for (int i = 0; i < coordinates.length(); i++) {
                    JSONArray point = coordinates.getJSONArray(i);
                    LatLng latLng = new LatLng(point.getDouble(1), point.getDouble(0)); // (latitude, longitude)
                    routes.add(latLng);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}