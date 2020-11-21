package com.aziz.ui.activity.question;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aziz.App;
import com.aziz.data.model.QuizResult;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.data.network.IQuizApiClient;

import java.util.ArrayList;

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<QuestionModel>> questionLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> pBar_number = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>(true);
    public ArrayList<QuestionModel> qm_list = new ArrayList<>();


    public void getQuestion(int id, int category, String difficulty) {
        App.repository.getQuestionModel(new IQuizApiClient.QuestionsCallBack() {
                                            @Override
                                            public void onSuccess(ArrayList<QuestionModel> result) {
                                                questionLiveData.setValue(result);
                                                qm_list.addAll(result);
                                                isLoading.set(false);
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                            }
                                        }, id, category, difficulty
        );
    }

    public void setpBar_number(Integer position) {
        pBar_number.setValue(position);
    }

}

