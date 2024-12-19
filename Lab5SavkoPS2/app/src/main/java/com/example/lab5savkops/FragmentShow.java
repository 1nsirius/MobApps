package com.example.lab5savkops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.List;
import android.util.Log;

public class FragmentShow extends Fragment {
    private ListView listView;
    private NotesDatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private List<Note> notes; // Список заметок для хранения объектов Note

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        listView = view.findViewById(R.id.listView);
        dbHelper = new NotesDatabaseHelper(getContext());

        // Инициализация адаптера
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        // Загрузка заметок
        loadNotes();

        // Обработчики нажатий на кнопки
        Button updateButton = view.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(v -> updateNote());

        Button deleteButton = view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> deleteNote());

        return view;
    }

    private void loadNotes() {
        notes = dbHelper.getAllNotes(); // Получаем все заметки
        adapter.clear(); // Очищаем адаптер
        for (Note note : notes) {
            adapter.add(note.getDescription()); // Добавляем описание заметки в адаптер
            Log.d("FragmentShow", "Loaded note: " + note.getDescription()); // Логируем каждую загруженную заметку
        }
        adapter.notifyDataSetChanged(); // Обновляем адаптер
    }

    public void updateNotes() {
        loadNotes(); // Перезагружаем заметки из базы данных
    }

    private void updateNote() {
        // Логика обновления заметки
        if (!notes.isEmpty()) {
            int id = notes.get(0).getId(); // Обновляем первую заметку (или реализуйте выбор заметки)
            String newDescription = "Updated Note"; // Получите новое описание от пользователя

            if (dbHelper.updateNote(id, newDescription)) {
                updateNotes(); // Обновляем список заметок
                Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to update note", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteNote() {
        // Логика удаления заметки
        if (!notes.isEmpty()) {
            int id = notes.get(0).getId(); // Удаляем первую заметку (или реализуйте выбор заметки)

            if (dbHelper.deleteNote(id)) {
                updateNotes(); // Обновляем список заметок
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to delete note", Toast.LENGTH_SHORT).show();
            }
        }
    }
}