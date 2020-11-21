package com.aziz.data.local;

import androidx.lifecycle.LiveData;

import com.aziz.data.model.QuizResult;

import java.util.ArrayList;

public class HistoryStorage implements IHistoryStorage {
    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<ArrayList<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void deleteAll() {

    }
}
