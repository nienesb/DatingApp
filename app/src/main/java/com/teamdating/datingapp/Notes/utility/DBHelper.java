package com.teamdating.datingapp.Notes.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teamdating.datingapp.R;
import com.teamdating.datingapp.Notes.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by btaluy on 27-3-2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    //version number needs to be changed when a new migration is getting added.
    private static final int DATABASE_VERSION = 1;

    // Name of the Database
    private static final String DATABASE_NAME = "Note.db";

    private final Context context;

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private static String getSimpleCurrentDate() {
        //formatter that will convert dates into the day-month-year format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //Today's date, but with time included, which we don't want
        Date today = new Date();
        //format.format returns a string
        return format.format(today);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        String CREATE_TABLE_NOTE = "CREATE TABLE " + Note.TABLE + '('
                + Note.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Note.KEY_TITLE + " TEXT, "
                + Note.KEY_PLATFORM + " TEXT, "
                + Note.KEY_DATE + " TEXT, "
                + Note.KEY_NOTES + " TEXT )";
        db.execSQL(CREATE_TABLE_NOTE);
        //Get the current date in numbered day-month-year format
        String curDate = getSimpleCurrentDate();


        // Fill database with Dummy Notes
        /*String[] titles = context.getResources().getStringArray(R.array.gamebacklog_game_titles);
        for (int i = 1; i < titles.length; i++) {
            ContentValues values = new ContentValues();
            values.put(Game.KEY_TITLE, titles[i]);
            values.put(Game.KEY_PLATFORM, "PC");
            values.put(Game.KEY_DATE, curDate);
            values.put(Game.KEY_STATUS, "Stalled");
            values.put(Game.KEY_NOTES, "I should stop playing");
            // Inserting Row
            db.insert(Game.TABLE, null, values);
        }*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE);
        // Create tables again
        onCreate(db);
    }
}
