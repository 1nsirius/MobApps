package com.example.lab5savkops;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note> {
    private List<Note> notes;

    public NotesAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
        this.notes = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }

        Note note = getItem(position);
        TextView numberTextView = convertView.findViewById(R.id.noteNumber);
        TextView descriptionTextView = convertView.findViewById(R.id.noteDescription);

        numberTextView.setText(String.valueOf(note.getNumber()));
        descriptionTextView.setText(note.getDescription());

        return convertView;
    }
}