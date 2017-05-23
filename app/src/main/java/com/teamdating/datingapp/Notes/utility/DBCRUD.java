package com.teamdating.datingapp.Notes.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teamdating.datingapp.Notes.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by btaluy on 23-05-2017.
 */

public class DBCRUD {
    private final DBHelper dbHelper;

    public DBCRUD(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void saveNote(Note note) {
        // Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.KEY_TITLE, note.getTitle());
        values.put(Note.KEY_PLATFORM, note.getPlatform());
        values.put(Note.KEY_DATE, note.getDateAdded());
        values.put(Note.KEY_NOTES, note.getNotes());
        // Inserting Row
        db.insert(Note.TABLE, null, values);
        db.close(); // Closing database connection
    }

    public List<Note> getNotes() // Get all notes
    {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Note.KEY_ID + ',' +
                Note.KEY_TITLE + ',' +
                Note.KEY_PLATFORM + ',' +
                Note.KEY_DATE + ',' +
                Note.KEY_NOTES +
                " FROM " + Note.TABLE;
        //User user = new User();
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.KEY_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(Note.KEY_TITLE)));
                note.setPlatform(cursor.getString(cursor.getColumnIndex(Note.KEY_PLATFORM)));
                note.setDateAdded(cursor.getString(cursor.getColumnIndex(Note.KEY_DATE)));
                note.setNotes(cursor.getString(cursor.getColumnIndex(Note.KEY_NOTES)));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public void modifyNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.KEY_TITLE, note.getTitle());
        values.put(Note.KEY_PLATFORM, note.getPlatform());
        values.put(Note.KEY_DATE, note.getDateAdded());
        values.put(Note.KEY_NOTES, note.getNotes());
        db.update(Note.TABLE, values, Note.KEY_ID + "= ?", new String[]{String.valueOf(note.getId())});
        db.close(); // Closing database connection
    }

    public void deleteNote(long user_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Note.TABLE, Note.KEY_ID + "= ?", new String[]{String.valueOf(user_Id)});
        db.close(); // Closing database connection
    }

    public void deleteAllNotes(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Note.TABLE, null, null);
        db.close(); // Closing database connection
    }
}
