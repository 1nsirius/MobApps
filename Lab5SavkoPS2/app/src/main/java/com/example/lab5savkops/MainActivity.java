package com.example.lab5savkops;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Начинаем транзакцию для добавления фрагментов
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Создаем экземпляры фрагментов
        FragmentAdd fragmentAdd = new FragmentAdd();
        FragmentShow fragmentShow = new FragmentShow();

        // Добавляем фрагменты в контейнеры
        fragmentTransaction.add(R.id.fragment_container_1, fragmentAdd);
        fragmentTransaction.add(R.id.fragment_container_2, fragmentShow);

        // Завершаем транзакцию
        fragmentTransaction.commit();
    }
}