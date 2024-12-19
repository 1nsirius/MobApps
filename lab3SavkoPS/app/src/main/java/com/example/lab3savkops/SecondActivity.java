package com.example.lab3savkops;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast; // Импортируйте Toast
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewNameSurname, textViewPhone, textViewRoute;
    private Button buttonSetPath, buttonCallTaxi;
    private ActivityResultLauncher<Intent> setPathLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("SecondActivity", "onCreate");

        textViewNameSurname = findViewById(R.id.textViewNameSurname);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewRoute = findViewById(R.id.textViewRoute);
        buttonSetPath = findViewById(R.id.buttonSetPath);
        buttonCallTaxi = findViewById(R.id.buttonCallTaxi);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");

        textViewNameSurname.setText(name + " " + surname);
        textViewPhone.setText(phone);

        // Инициализация ActivityResultLauncher
        setPathLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    String route = data.getStringExtra("route");
                    textViewRoute.setText("Маршрут: " + route);
                    buttonCallTaxi.setEnabled(true); // Активируем кнопку "Call Taxi"
                    buttonCallTaxi.setOnClickListener(view -> {
                        // Выводим всплывающее сообщение
                        Toast.makeText(SecondActivity.this, "Такси успешно отправлено!", Toast.LENGTH_SHORT).show();
                        Log.d("SecondActivity", "Такси вызвано");
                    });
                }
            }
        });

        buttonSetPath.setOnClickListener(view -> {
            Intent setPathIntent = new Intent(SecondActivity.this, ThirdActivity.class);
            setPathLauncher.launch(setPathIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SecondActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SecondActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SecondActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SecondActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }
}