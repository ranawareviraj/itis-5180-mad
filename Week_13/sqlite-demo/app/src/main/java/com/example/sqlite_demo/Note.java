package com.example.sqlite_demo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public long _id;

    @ColumnInfo
    public String subject;

    @ColumnInfo
    public String note;

    public Note(long _id, String subject, String note) {
        this._id = _id;
        this.subject = subject;
        this.note = note;
    }

    public Note(String subject, String note) {
        this.subject = subject;
        this.note = note;
    }

    public Note() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", subject='" + subject + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
