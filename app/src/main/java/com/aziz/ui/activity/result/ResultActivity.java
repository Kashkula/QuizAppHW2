package com.aziz.ui.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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
    protected float idF, forAnswerF;
    protected int id, forAnswer;
    protected ActivityResultBinding binding;
    protected ResultViewModel vm;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        vm = new ViewModelProvider(this).get(ResultViewModel.class);

        binding.setRa(this);

        Intent intent = getIntent();
        QuizResult qr = vm.getQuizResult(intent.getStringExtra(QuestionViewModel.QUIZ_RESULT));
        vm.saveToDB(qr);

        idF = qr.getAmount();
        forAnswerF = qr.getCorrectAnswerResult();

        binding.textUnderDifficulty.setText(qr.getDifficulty());
        binding.textUnderCorrectAnswers.setText(qr.getCorrectAnswerResult() + "/" + qr.getAmount());
        binding.textUnderResult.setText(forAnswerF / idF * 100 + "%");
        binding.textViewVar.setText(qr.getCategory());

        binding.btnFinish.setOnClickListener(v -> {
            finish();
        });
    }
}