package com.aziz.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aziz.R;
import com.aziz.databinding.ActivityResultBinding;
import com.aziz.ui.activity.question.QuestionActivity;
import com.aziz.ui.fragment.main.MainFragment;

public class ResultActivity extends AppCompatActivity {
    protected String difficulty;
    float idF, forAnswerF;
    int id, category, forAnswer;
    protected ActivityResultBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra(MainFragment.DIFFICULTY);
        id = intent.getIntExtra(MainFragment.ID, 0);
        category = intent.getIntExtra(MainFragment.CATEGORY, 0);
        forAnswer = intent.getIntExtra(QuestionActivity.CORRECT_ANSWER, 0);
        idF = id;
        forAnswerF = forAnswer;

        binding.textUnderDifficulty.setText(difficulty);
        binding.textUnderCorrectAnswers.setText(forAnswer + "/" + id);
        binding.textUnderResult.setText(forAnswerF / idF * 100 + "%");

        binding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}