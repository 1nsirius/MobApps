package com.example.lab8savkops;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LatLng userLocation = new LatLng(0, 0); // Замените на ваше начальное местоположение

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Здесь можно добавить код для получения местоположения
        // Например, используя LocationManager или другие методы
        getUserLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarker();
        loadGeoapifyMap();
    }

    private void addMarker() {
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Вы находитесь здесь"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
    }

    private void getUserLocation() {
        // Используйте ваш метод получения местоположения
        // Например, через LocationManager или другие способы
        // В этом примере просто присваиваем фиксированные координаты
        userLocation = new LatLng(55.7558, 37.6173); // Пример: Москва
    }

    private void loadGeoapifyMap() {
        // Создайте экземпляр Retrofit и сделайте запрос к Geoapify API
        GeoapifyService geoapifyService = RetrofitClientInstance.getRetrofitInstance().create(GeoapifyService.class);

        Call<GeoapifyResponse> call = geoapifyService.getMap(userLocation.latitude, userLocation.longitude, "YOUR_API_KEY");
        call.enqueue(new Callback<GeoapifyResponse>() {
            @Override
            public void onResponse(Call<GeoapifyResponse> call, Response<GeoapifyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Обработка успешного ответа
                    // Например, обновление карты с использованием данных из Geoapify
                    Toast.makeText(MainActivity.this, "Карта загружена", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка загрузки карты", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeoapifyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}