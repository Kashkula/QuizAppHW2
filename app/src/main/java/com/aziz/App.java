package com.aziz;

import android.app.Application;

import com.aziz.data.local.HistoryStorage;
import com.aziz.data.local.IHistoryStorage;
import com.aziz.data.local.QuizRepository;
import com.aziz.data.network.QuizApiClient;

public class App extends Application {
    public static QuizApiClient apiClient;
    public static QuizRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();
        IHistoryStorage historyStorage = new HistoryStorage();

        repository = new QuizRepository(apiClient, historyStorage);
        
        //oijo
    }
}
