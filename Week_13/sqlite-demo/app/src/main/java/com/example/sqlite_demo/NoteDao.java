package com.example.sqlite_demo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
interface NoteDao {

    @Insert
    void insert(Note note);

    @Insert
    void insertAll(Note... notes);

    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE _id = :id")
    Note findById(long id);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note")
    void deleteAll();
}
