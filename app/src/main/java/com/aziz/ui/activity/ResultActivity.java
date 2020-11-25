package com.aziz.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aziz.R;
import com.aziz.data.model.QuizResult;
import com.aziz.databinding.ActivityResultBinding;
import com.aziz.ui.activity.question.QuestionViewModel;
import com.aziz.ui.fragment.main.MainFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ResultActivity extends AppCompatActivity {
    public String difficulty, categoryStr;
    float idF, forAnswerF;
    int id, forAnswer;
    protected ActivityResultBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        binding.setRa(this);


        Intent intent = getIntent();
        QuizResult qr = getQuizResult(intent.getStringExtra(QuestionViewModel.QUIZ_RESULT));

        assert qr != null;
        id = qr.getAmount();
        difficulty = qr.getDifficulty();
        categoryStr = qr.getCategory();


        forAnswer = qr.getCorrectAnswerResult();

        idF = id;
        forAnswerF = forAnswer;

        binding.textUnderDifficulty.setText(difficulty);
        binding.textUnderCorrectAnswers.setText(forAnswer + "/" + id);
        binding.textUnderResult.setText(forAnswerF / idF * 100 + "%");
        binding.textViewVar.setText(categoryStr);

        binding.btnFinish.setOnClickListener(v -> {
            finish();
        });

    }

    private QuizResult getQuizResult(String stringExtra) {
        Gson gson = new Gson();
        Type type = new TypeToken<QuizResult>() {
        }.getType();
        return gson.fromJson(stringExtra, type);
    }


}