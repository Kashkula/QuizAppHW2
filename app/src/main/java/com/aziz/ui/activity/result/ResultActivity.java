package com.aziz.ui.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.aziz.App;
import com.aziz.R;
import com.aziz.data.model.QuizResult;
import com.aziz.databinding.ActivityResultBinding;
import com.aziz.ui.activity.question.QuestionViewModel;
import com.aziz.ui.fragment.setting.SettingFragment;

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
        SharedPreferences sp = getSharedPreferences(SettingFragment.SHARED,MODE_PRIVATE);
        setMyTheme(sp.getInt(SettingFragment.THEME, 20));

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

    public void setMyTheme(int theme) {
        switch (theme) {
            case 0:
                setTheme(R.style.Red);
                break;
            case 1:
                setTheme(R.style.Orange);
                break;
            case 2:
                setTheme(R.style.Blue);
                break;
            case 3:
                setTheme(R.style.Dark);
                break;
            case 4:
                setTheme(R.style.Green);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }
    }

}