package com.aziz.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aziz.data.model.QuizResult;

@Database(entities = {QuizResult.class}, version = 1, exportSchema = false)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
