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
import com.aziz.ui.activity.ResultActivity;
import com.aziz.ui.fragment.main.MainFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.aziz.ui.activity.question.QuestionActivity.CORRECT_ANSWER;

@SuppressLint("SetTextI18n")

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<QuestionModel>> questionLiveData = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>(true);
    public ArrayList<QuestionModel> qm_list = new ArrayList<>();
    protected String difficulty, categoryStr;
    public int id, category, position = 0, forAnswer = 0;
    public static final String QUIZ_RESULT = "quizResult";


    public void getFromIntent(Intent intent) {
        id = intent.getIntExtra(MainFragment.ID, 22);
        category = intent.getIntExtra(MainFragment.CATEGORY, 23);
        difficulty = intent.getStringExtra(MainFragment.DIFFICULTY);
        categoryStr = intent.getStringExtra(MainFragment.CATEGORY_STR);

        Log.d("TAG", "getFromIntent: " + " category: " + category + " difficulty: " + difficulty + " id: " + id);
        getQuestion(id, category, difficulty);
    }

    public void getQuestion(int id, int category, String difficulty) {
        App.repository.getQuestionModel(new IQuizApiClient.QuestionsCallBack() {
                                            @Override
                                            public void onSuccess(ArrayList<QuestionModel> result) {
                                                if (result != null) {
                                                    questionLiveData.setValue(result);
                                                    qm_list.clear();
                                                    qm_list.addAll(result);
                                                    isLoading.set(false);
                                                    Log.d("TAG", "onSuccess: ");
                                                    Log.d("TAG", "second: " + result.size());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Exception e) {
                                                Log.d("TAG", "onFailure: ");
                                            }
                                        }, id, category, difficulty
        );
    }

    public void getQuestionLiveData(QuestionActivity questionActivity, final QuestionAdapter adapter) {
        questionLiveData.observe(questionActivity, adapter::setQuestions);
    }

    public void sendIntent(QuestionActivity qa) {
        QuizResult quizResult = new QuizResult(categoryStr, difficulty, forAnswer, new Date(System.currentTimeMillis()), qm_list, id);

        Intent intent = new Intent(qa, ResultActivity.class);
        intent.putExtra(QUIZ_RESULT, new Gson().toJson(quizResult));
        qa.setResult(RESULT_OK, intent);
        qa.startActivity(intent);
        qa.finish();
    }

    public void beginPBar(ProgressBar pBar, TextView tvQuality) {
        pBar.setMax(id);
        tvQuality.setText(0 + "/" + pBar.getMax());
    }

    public void nextItem(boolean correctAnswer, final int adapterPosition, final RecyclerView rv) {
        if (correctAnswer)
            forAnswer++;
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                position = adapterPosition + 1;
                rv.scrollToPosition(position);
            }
        }.start();
    }

    public void onSkip(RecyclerView rv) {
        if (position < id)
            rv.scrollToPosition(position += 1);

    }

    public void outback(QuestionActivity qa, RecyclerView rv) {
        if (position > 0)
            rv.scrollToPosition(position -= 1);
        else qa.finish();
    }
}

