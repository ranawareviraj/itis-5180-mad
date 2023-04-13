package com.example.assessment8.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DrinkDao {

    @Insert
    public void insert(Drink drink);

    @Insert
    public void insertAll(Drink... drinks);

    @Update
    public void update(Drink drink);

    @Delete
    public void deleteDrink(Drink drink);

    @Query("DELETE FROM drinks")
    public void deleteAll();

    @Query("SELECT * FROM drinks")
    public List<Drink> getAllDrinks();

    @Query("SELECT * FROM drinks WHERE id = :id")
    public Drink findById(int id);
}
