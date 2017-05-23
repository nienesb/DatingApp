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

import com.teamdating.datingapp.Notes.model.Note;
import com.teamdating.datingapp.Notes.utility.DBCRUD;
import com.teamdating.datingapp.R;

public class EditNote extends AppCompatActivity {

    private DBCRUD mDataSource;
    private ArrayAdapter statusAdapter;
    private EditText mTitle;
    private EditText mPlatform;
    private EditText mNotes;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editnote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Note");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeTextViews();
        Intent intent = getIntent();
        mNote = (Note) intent.getSerializableExtra("selectedNote");

        setNoteToInput(mNote);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyNote();
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
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onResume();  // Always call the superclass method first
        // Save Note and go back to MainActivity
        modifyNote();
    }

    public void initializeTextViews(){
        mTitle = (EditText) findViewById(R.id.note_edit_item);
        mPlatform = (EditText) findViewById(R.id.note_edit_platform);
        mNotes = (EditText) findViewById(R.id.note_edit_notes);
    }

    public void setNoteToInput(Note note) {
        if(note != null) {
            mTitle.setText(note.getTitle());
            mPlatform.setText(note.getPlatform());
            mNotes.setText(note.getNotes());
        }
    }

    void modifyNote() {
        // Get the input from the Views
        String title = mTitle.getText().toString();
        String platform = mPlatform.getText().toString();
        String notes = mNotes.getText().toString();
        if ((title != null) && title.isEmpty()) {
            // Make EditText titleInput display an error message, and display a toast
            // That the title field is empty
            EditNote.setErrorText(mTitle, getString(R.string.note_title_is_required));
            showToast(getString(R.string.note_title_field_is_empty));
        } else if ((platform != null) && platform.isEmpty()) {
            // Make EditText platformInput display an error message, and display a toast
            // That the platform field is empty
            EditNote.setErrorText(mPlatform, getString(R.string.note_platform_is_required));
            showToast(getString(R.string.note_platform_field_is_empty));
        } else {
            // Update the note with the new data
            mNote.setTitle(title);
            mNote.setPlatform(platform);
            mNote.setNotes(notes);
            // Create a DBCRUD object, and pass it the context of this activity
            DBCRUD dbcrud = new DBCRUD(this);
            dbcrud.modifyNote(mNote);
            //Notify the user of the success
            showToast(getString(R.string.note_has_been_modified));
            // Starting the previous Intent
            Intent previousActivity = new Intent(this, EditNoteActivity.class);
            // Sending the data to NoteDetailsActivity
            previousActivity.putExtra("selectedNote", mNote);
            setResult(1000, previousActivity);
            finish();
        }
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
}
