package com.example.lab3savkops;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3savkops.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhone, editTextName, editTextSurname;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Загрузка данных
        SharedPreferences sharedPreferences = getSharedPreferences("TaxiAppPrefs", MODE_PRIVATE);
        editTextPhone.setText(sharedPreferences.getString("phone", ""));
        editTextName.setText(sharedPreferences.getString("name", ""));
        editTextSurname.setText(sharedPreferences.getString("surname", ""));

        if (!editTextPhone.getText().toString().isEmpty()) {
            buttonRegister.setText("Log in");
        }

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString();
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();

                // Сохранение данных
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phone", phone);
                editor.putString("name", name);
                editor.putString("surname", surname);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("name", name);
                intent.putExtra("surname", surname);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }
}