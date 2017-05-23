package com.teamdating.datingapp.Notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.teamdating.datingapp.Notes.adapter.NoteListItemAdapter;
import com.teamdating.datingapp.Notes.utility.DBCRUD;
import com.teamdating.datingapp.Notes.model.Note;
import com.teamdating.datingapp.Activities.MenuActivity;
import com.teamdating.datingapp.R;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private RecyclerView noteListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DBCRUD dbcrud;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notities");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create a DBCRUD object, and pass it the context of this activity
        dbcrud = new DBCRUD(this);
        // Get the list of notes from Database
        notes = dbcrud.getNotes();
        NoteListItemAdapter noteAdapter = new NoteListItemAdapter(notes, this);

        noteListView = (RecyclerView) findViewById(R.id.noteList);
        mLayoutManager = new LinearLayoutManager(this);
        noteListView.setLayoutManager(mLayoutManager);
        noteListView.setHasFixedSize(true);
        noteListView.setAdapter(noteAdapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(100L);
        itemAnimator.setRemoveDuration(100L);
        noteListView.setItemAnimator(itemAnimator);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
            startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        // Create a DBCRUD object, and pass it the context of this activity
        DBCRUD dbcrud = new DBCRUD(this);
        // Get the list of notes from Database
        notes = dbcrud.getNotes();
        NoteListItemAdapter noteAdapter = new NoteListItemAdapter(notes, this);
        noteAdapter.updateList(notes);
        noteAdapter.notifyDataSetChanged();
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

        if (id == R.id.action_delete_item) {
            if(dbcrud.getNotes().size() > 0) {
                new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to delete all the notes from your list?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dbcrud.deleteAllNotes();
                            updateUi();
                            Toast.makeText(NoteActivity.this, "emptied your list", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            } else {
                Toast.makeText(NoteActivity.this, "No notes were found", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        if(id == 16908332) {
            Intent intent = new Intent(NoteActivity.this, MenuActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUi() {
        List<Note> notes = dbcrud.getNotes();
        NoteListItemAdapter noteAdapter = new NoteListItemAdapter(notes, this);
        noteAdapter.updateList(notes);
        noteAdapter.notifyDataSetChanged();

        mLayoutManager = new LinearLayoutManager(this);
        noteListView.setLayoutManager(mLayoutManager);
        noteListView.setHasFixedSize(true);
        noteListView.setAdapter(noteAdapter);
    }
}
