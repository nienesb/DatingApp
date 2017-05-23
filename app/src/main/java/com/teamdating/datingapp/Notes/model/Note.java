package com.teamdating.datingapp.Notes.model;

import java.io.Serializable;

/**
 * Created by btaluy on 23-05-2017.
 */

public class Note implements Serializable {
    // Labels table name
    public static final String TABLE = "notes";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_DATE = "date";
    public static final String KEY_NOTES = "notes";

    // Property help us to keep data
    private int id;
    private String title;
    private String platform;
    private String dateAdded; // String, since you cannot save date/time values in SQLite
    private String notes;

    public Note(int id, String title, String platform, String dateAdded, String notes) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.dateAdded = dateAdded;
        this.notes = notes;
    }

    public Note() {

    }

    public long getId() {
        return (long) id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
