package edu.uncc.inclass09;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GradeDao {
    @Insert
    public void insert(Grade grade);

    @Insert
    public void insertAll(Grade... grades);

    @Update
    public void update(Grade grade);

    @Delete
    public void deleteGrade(Grade grade);

    @Query("DELETE FROM grade")
    public void deleteAll();

    @Query("SELECT * FROM grade")
    public List<Grade> getAllGrades();

    @Query("SELECT * FROM grade WHERE id = :id")
    public Grade findById(int id);

}
