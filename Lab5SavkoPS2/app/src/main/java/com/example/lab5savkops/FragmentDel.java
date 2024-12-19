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


public class FragmentDel extends Fragment {
    private EditText editText;
    private Button delButton;
    private NotesDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_del, container, false);
        editText = view.findViewById(R.id.editText);
        delButton = view.findViewById(R.id.delButton);
        dbHelper = new NotesDatabaseHelper(getContext());

        delButton.setOnClickListener(v -> deleteNote());
        return view;
    }

    private void deleteNote() {
        String idString = editText.getText().toString();
        if (!idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            if (dbHelper.deleteNote(id)) {
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
                editText.setText("");
            } else {
                Toast.makeText(getContext(), "Note not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Please enter a note number", Toast.LENGTH_SHORT).show();
        }
    }
}