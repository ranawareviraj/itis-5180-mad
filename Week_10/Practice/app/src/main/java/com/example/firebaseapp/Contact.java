package com.example.firebaseapp;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {
    String name;
    String cell;

    public Contact(String name, String cell) {
        this.name = name;
        this.cell = cell;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
