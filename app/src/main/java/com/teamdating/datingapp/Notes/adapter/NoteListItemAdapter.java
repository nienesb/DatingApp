package com.teamdating.datingapp.Notes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamdating.datingapp.Notes.EditNoteActivity;
import com.teamdating.datingapp.Notes.model.Note;
import com.teamdating.datingapp.R;

import java.util.List;

/**
 * Created by btaluy on 23-05-2017.
 */

public class NoteListItemAdapter extends RecyclerView.Adapter<NoteListItemAdapter.ViewHolder> {
    final Context context;
    private final List<Note> noteArrayList;
    public NoteListItemAdapter(List<Note> list, Context context) {
        noteArrayList = list;
        this.context = context;
    }
    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }
    private Note getItem(int position) {
        return noteArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return noteArrayList.get(position).getId();
    }
    public void updateList(List<Note> newlist) {
        // Set new updated list
        noteArrayList.clear();
        noteArrayList.addAll(newlist);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_note_item, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Populate the row
        holder.populateRow(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final TextView platform;
        private final TextView date;
        //initialize the variables
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.Note_item_title);
            platform = (TextView) view.findViewById(R.id.Note_item_platform);
            date = (TextView) view.findViewById(R.id.Note_item_date);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, EditNoteActivity.class);
            // Get the correct note based on which listitem got clicked, and put it as parameter in the intent
            Note selectedNote = getItem(getAdapterPosition());
            intent.putExtra("selectedNote", selectedNote);
            // Open NoteDetailsActivity
            context.startActivity(intent);
        }
        public void populateRow(Note note) {
            title.setText(note.getTitle());
            platform.setText(note.getPlatform());
            date.setText(note.getDateAdded());
        }
    }
}
