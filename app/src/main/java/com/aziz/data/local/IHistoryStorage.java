package com.aziz.data.local;

import com.aziz.data.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    void saveQuizResult(QuizResult quizResult);

    List<QuizResult> getAll();

    void delete(int id);

    void deleteAll();

}
