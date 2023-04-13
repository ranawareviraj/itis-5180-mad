package com.example.sqlite_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "notes_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        db.noteDao().deleteAll();

        // Add Notes
        db.noteDao().insertAll(new Note("Note 1", "This is note 1"),
                new Note("Note 2", "This is note 2"),
                new Note("Note 3", "This is note 3"),
                new Note("Note 4", "This is note 4"),
                new Note("Note 5", "This is note 5"),
                new Note("Note 6", "This is note 6"),
                new Note("Note 7", "This is note 7"),
                new Note("Note 8", "This is note 8"),
                new Note("Note 9", "This is note 9"),
                new Note("Note 10", "This is note 10"));

        TextView textView = findViewById(R.id.textView);
        List<Note> notes = db.noteDao().getAllNotes();
        textView.setText(notes.toString());
        db.noteDao().getAllNotes();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
}