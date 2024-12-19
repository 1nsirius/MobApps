package com.example.lab5savkops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.util.Log;

public class FragmentAdd extends Fragment {
    private EditText editText;
    private Button addButton;
    private NotesDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        editText = view.findViewById(R.id.editText);
        addButton = view.findViewById(R.id.addButton);
        dbHelper = new NotesDatabaseHelper(getContext());

        addButton.setOnClickListener(v -> addNote());
        return view;
    }

    private void addNote() {
        String description = editText.getText().toString();
        if (!description.isEmpty()) {
            dbHelper.addNote(new Note(0, description, 0)); // Передаем номер заметки как 0
            Log.d("FragmentAdd", "Note added: " + description);
            Toast.makeText(getContext(), "Note added", Toast.LENGTH_SHORT).show();
            editText.setText("");

            // Обновляем FragmentShow
            FragmentShow fragmentShow = (FragmentShow) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_2);
            if (fragmentShow != null) {
                fragmentShow.updateNotes();
            }
        } else {
            Toast.makeText(getContext(), "Please enter a description", Toast.LENGTH_SHORT).show();
        }
    }
}