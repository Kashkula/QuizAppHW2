package com.aziz.ui.activity.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.App;
import com.aziz.data.adapter.question.QuestionAdapter;
import com.aziz.data.model.QuizResult;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.data.network.IQuizApiClient;
import com.aziz.ui.activity.result.ResultActivity;
import com.aziz.ui.fragment.main.MainFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

@SuppressLint("SetTextI18n")

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<QuestionModel>> questionLiveData = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>(true);
    public ArrayList<QuestionModel> qm_list = new ArrayList<>();
    protected String difficulty, categoryStr;
    public int id, category, position = 0, forAnswer = 0;
    public static final String QUIZ_RESULT = "quizResult";

    public void getQuestion() {
        App.repository.getQuestionModel(new IQuizApiClient.QuestionsCallBack() {
                                            @Override
                                            public void onSuccess(ArrayList<QuestionModel> result) {
                                                if (result != null) {
                                                    questionLiveData.setValue(result);
                                                    qm_list.clear();
                                                    qm_list.addAll(result);
                                                    isLoading.set(false);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                                Log.d("TAG", "onFailure: ");
                                            }
                                        }, id, category, difficulty
        );
    }
}

