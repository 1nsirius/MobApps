package com.example.lab5savkops;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class NotesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_NUMBER = "number"; // Добавляем новое поле для номера заметки

    private int noteCounter = 0; // Счетчик для номера заметки

    public NotesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NOTES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_NUMBER + " INTEGER)"; // Убедитесь, что столбец 'number' существует
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    // Метод для добавления заметки
    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, note.getDescription());
        values.put(COLUMN_NUMBER, noteCounter++);
        long result = db.insert(TABLE_NOTES, null, values);

        Log.d("DatabaseHelper", "Add Note Result: " + result);

        if (result == -1) {
            Log.e("DatabaseHelper", "Error adding note");
        } else {
            Log.d("DatabaseHelper", "Note added with ID: " + result);
        }
        db.close();
    }

    // Метод для получения всех заметок
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String[] columns = {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_NUMBER};
            cursor = db.query(TABLE_NOTES, columns, null, null, null, null, null);

            Log.d("DatabaseHelper", "Total notes found: " + cursor.getCount());

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                    int number = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUMBER));
                    notes.add(new Note(id, description, number));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return notes;
    }

    // Метод для удаления заметки
    public boolean deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NOTES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0; // Возвращаем true, если заметка была удалена
    }

    // Метод для обновления заметки
    public boolean updateNote(int id, String newDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, newDescription);
        int rowsAffected = db.update(TABLE_NOTES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0; // Возвращаем true, если заметка была обновлена
    }
}