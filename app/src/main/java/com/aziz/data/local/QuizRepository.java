package com.aziz.data.local;

import androidx.lifecycle.LiveData;

import com.aziz.data.model.QuizResult;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.data.network.IQuizApiClient;

import java.util.ArrayList;
import java.util.Collections;

public class QuizRepository implements IQuizApiClient, IHistoryStorage {

    protected IQuizApiClient quizApiClient;
    protected IHistoryStorage historyStorage;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
    }

    @Override
    public void getQuestionModel(final QuestionsCallBack callBack, int amount, int category, String difficulty) {
        quizApiClient.getQuestionModel(new QuestionsCallBack() {
            @Override
            public void onSuccess(ArrayList<QuestionModel> result) {
                for (int i = 0; i < result.size(); i++) {
                    QuestionModel qm = result.get(i);
                    ArrayList<String> answers_list = new ArrayList<>(qm.getIncorrectAnswers());
                    answers_list.add(qm.getCorrectAnswer());
                    Collections.shuffle(answers_list);
                    result.get(i).setIncorrectAnswers(answers_list);
                }
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
            }

        }, amount, category, difficulty);
    }

    @Override
    public void getCategory(CategoryCallBack callBack) {
    }

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
