package com.aziz;

import android.app.Application;

import androidx.room.Room;

import com.aziz.data.db.QuizDataBase;
import com.aziz.data.local.HistoryStorage;
import com.aziz.data.local.IHistoryStorage;
import com.aziz.data.local.QuizRepository;
import com.aziz.data.network.QuizApiClient;

public class App extends Application {
    public static QuizApiClient apiClient;
    public static QuizRepository repository;
    public static QuizDataBase db;
    public static String theme = "str";

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();
        IHistoryStorage historyStorage = new HistoryStorage();

        repository = new QuizRepository(apiClient, historyStorage);

        db = Room.databaseBuilder(getApplicationContext(),
                QuizDataBase.class, "Quizdb").allowMainThreadQueries().build();
    }
}
