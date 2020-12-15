package com.aziz.ui.activity.result;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.aziz.App;
import com.aziz.data.model.QuizResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ResultViewModel extends ViewModel {


    protected ObservableField<String> difficulty = new ObservableField<>();
    protected ObservableField<String> category = new ObservableField<>();
    protected ObservableField<String> id = new ObservableField<>();
    protected ObservableField<String> forAnswer = new ObservableField<>();

    public void saveToDB(QuizResult quizResult) {
        App.repository.saveQuizResult(quizResult);
    }

    public QuizResult getQuizResult(String stringExtra) {
        Gson gson = new Gson();
        Type type = new TypeToken<QuizResult>() {
        }.getType();
        return gson.fromJson(stringExtra, type);
    }

    public void getInformation(QuizResult m) {
        difficulty.set(m.getDifficulty());
        category.set(m.getCategory());
        id.set(String.valueOf(m.getAmount()));
        this.forAnswer.set(String.valueOf(m.getCorrectAnswerResult()));
    }


}
