package com.teamdating.datingapp.Notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.teamdating.datingapp.Notes.model.Note;
import com.teamdating.datingapp.Notes.utility.DBCRUD;
import com.teamdating.datingapp.R;

public class EditNoteActivity extends AppCompatActivity {

    private DBCRUD mDataSource;
    private TextView mTitle;
    private TextView mPlatform;
    private TextView mDateAdded;
    private TextView mNotes;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeTextViews();
        mDataSource = new DBCRUD(this);
        mNote = (Note) getIntent().getSerializableExtra("selectedNote");

        getSupportActionBar().setTitle("Note information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle.setText(mNote.getTitle());
        mPlatform.setText(mNote.getPlatform());
        mDateAdded.setText(mNote.getDateAdded());
        mNotes.setText(mNote.getNotes());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditNoteActivity.this, EditNote.class);
                intent.putExtra("selectedNote", mNote);
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Set the Note Card with the updated note
        mNote = (Note) data.getSerializableExtra("selectedNote");
        setNoteToInput(mNote); // Your method where the textviews are set
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_item) {
            if(mNote != null ) {
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to delete this Note from your list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            mDataSource.deleteNote(mNote.getId());
                            showNoteDeletedToast();
                                Intent intent = new Intent(EditNoteActivity.this, NoteActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else {
                Toast.makeText(EditNoteActivity.this, "Note not found", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        if(id == 16908332) {
            Intent intent = new Intent(EditNoteActivity.this, NoteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNoteDeletedToast() {
        Context context = getApplicationContext();
        String text = String.format("%s %s", mNote.getTitle(), getString(R.string.note_deleted));
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void initializeTextViews(){
        mTitle = (TextView) findViewById(R.id.note_detail_item_title);
        mPlatform = (TextView) findViewById(R.id.note_show_item_platform);
        mDateAdded = (TextView) findViewById(R.id.note_show_item_dateAdded);
        mNotes = (TextView) findViewById(R.id.note_show_item_notes);
    }

    public void setNoteToInput(Note note) {
        if(note != null) {
            mTitle.setText(note.getTitle());
            mPlatform.setText(note.getPlatform());
            mDateAdded.setText(note.getDateAdded());
            mNotes.setText(note.getNotes());
        }
    }

}
