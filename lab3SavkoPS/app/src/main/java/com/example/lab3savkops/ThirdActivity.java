package com.example.lab3savkops;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3savkops.R;


import com.example.lab3savkops.R;

public class ThirdActivity extends AppCompatActivity {

    private EditText[] routeParams = new EditText[6];
    private Button buttonOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d("ThirdActivity", "onCreate");

        routeParams[0] = findViewById(R.id.editTextRouteParam1);
        routeParams[1] = findViewById(R.id.editTextRouteParam2);
        routeParams[2] = findViewById(R.id.editTextRouteParam3);
        routeParams[3] = findViewById(R.id.editTextRouteParam4);
        routeParams[4] = findViewById(R.id.editTextRouteParam5);
        routeParams[5] = findViewById(R.id.editTextRouteParam6);
        buttonOK = findViewById(R.id.buttonOK);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder route = new StringBuilder();
                for (EditText param : routeParams) {
                    route.append(param.getText().toString()).append(" ");
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("route", route.toString().trim());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ThirdActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ThirdActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ThirdActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ThirdActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ThirdActivity", "onDestroy");
    }
}