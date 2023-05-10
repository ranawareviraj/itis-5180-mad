package com.example.finalexam.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class CoverPhoto implements Serializable {
    public String id;
    public Date created_at;
    public String description;
    public Urls urls;
    public User user;

    @Override
    public boolean equals(Object obj){

        return this.hashCode() == obj.hashCode();

  }

    @Override
    public int hashCode()
    {
        return this.id.hashCode();
    }
}








