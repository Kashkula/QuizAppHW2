package com.aziz.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aziz.data.model.QuizResult;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insert(QuizResult quizResult);

    @Query("SELECT * FROM QuizResult")
    List<QuizResult> getAll();

    @Query("DELETE FROM quizresult")
    void deleteAll();

    @Query("DELETE FROM QuizResult WHERE id = :id")
    void deleteByID(int id);

    @Update
    void update(QuizResult quizResult);
}
