package com.teamdating.datingapp.Notes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.teamdating.datingapp.Notes.utility.DBCRUD;
import com.teamdating.datingapp.Notes.model.Note;
import com.teamdating.datingapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mTitleAddText;
    private EditText mPlatformAddText;
    private EditText mNotesAddText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Note");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleAddText = (EditText) findViewById(R.id.note_add_item);
        mTitleAddText.clearFocus();
        mPlatformAddText = (EditText) findViewById(R.id.note_add_platform);
        mNotesAddText = (EditText) findViewById(R.id.note_add_notes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == 16908332) {
            Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static String getSimpleCurrentDate() {
        // Formatter that will convert dates into the day-month-year format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //Today's date, but with time included, which we don't want
        Date today = new Date();
        // Format.format returns a string
        return format.format(today);
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }


    private static void setErrorText(EditText editText, String message) {
        // Get the color white in integer form
        int RGB = Color.argb(255, 255, 0, 0);
        // Object that contains the color white
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(RGB);
        // Object that will hold the message, and makes it possible to change the color of the text
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
        // Give the message from the first till the last character a white color.
        // The last '0' means that the message should not display additional behaviour
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
        // Make the EditText display the error message
        editText.setError(ssbuilder);
    }

    public void saveNote() {
        // Get the current date in numbered day-month-year format
        String curDate = AddNoteActivity.getSimpleCurrentDate();
        // Retrieve the input from the user
        String title = mTitleAddText.getText().toString();
        String platform = mPlatformAddText.getText().toString();
        String notes = mNotesAddText.getText().toString();
        if ((title != null) && title.isEmpty()) {
            // Make EditText titleInput display an error message, and display a toast
            // That the title field is empty
            AddNoteActivity.setErrorText(mTitleAddText, getString(R.string.note_title_is_required));
            showToast(getString(R.string.note_title_field_is_empty));
        } else if ((platform != null) && platform.isEmpty()) {
            // Make EditText platformInput display an error message, and display a toast
            // That the platform field is empty
            AddNoteActivity.setErrorText(mPlatformAddText, getString(R.string.note_platform_is_required));
            showToast(getString(R.string.note_platform_field_is_empty));
        } else {
            // Create a DBCRUD object, and pass it the context of this activity
            DBCRUD dbcrud = new DBCRUD(this);
            // Make a note object based on the input. The correct id will be set in DBCRUD.saveNote()
            Note note = new Note(-1, title, platform, curDate, notes);
            // Save the note to the Database
            dbcrud.saveNote(note);
            // Notify the user with a toast that the note has been added
            showToast(getString(R.string.note_has_been_added));
            // Go back to MainActivity
            Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
            startActivity(intent);
        }
    }
}
