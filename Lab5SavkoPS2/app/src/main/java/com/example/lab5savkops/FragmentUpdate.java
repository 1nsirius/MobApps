package com.example.lab5savkops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;


public class FragmentUpdate extends Fragment {
    private EditText editTextId, editTextDescription;
    private Button updateButton;
    private NotesDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        editTextId = view.findViewById(R.id.editTextId);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        updateButton = view.findViewById(R.id.updateButton);
        dbHelper = new NotesDatabaseHelper(getContext());

        updateButton.setOnClickListener(v -> updateNote());
        return view;
    }

    private void updateNote() {
        String idString = editTextId.getText().toString();
        String newDescription = editTextDescription.getText().toString();

        if (!idString.isEmpty() && !newDescription.isEmpty()) {
            int id = Integer.parseInt(idString);
            if (dbHelper.updateNote(id, newDescription)) {
                Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();
                editTextId.setText("");
                editTextDescription.setText("");
            } else {
                Toast.makeText(getContext(), "Note not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }
}