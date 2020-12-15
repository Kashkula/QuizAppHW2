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
    protected ActivityResultBinding binding;
    protected ResultViewModel vm;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();

        init();
        getQuizResult();
        setText();
        onClick();

    }

    private void getQuizResult() {
        Intent intent = getIntent();
        QuizResult qr = vm.getQuizResult(intent.getStringExtra(QuestionViewModel.QUIZ_RESULT));
        vm.saveToDB(qr);
        vm.getInformation(qr);
    }


    private void onClick() {
        binding.btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    @SuppressLint("SetTextI18n")
    private void setText() {
        binding.textUnderDifficulty.setText(vm.difficulty.get());
        binding.textUnderCorrectAnswers.setText(vm.forAnswer.get() + "/" + vm.id.get());
        binding.textUnderResult.setText(Float.parseFloat(vm.forAnswer.get()) / Float.parseFloat(vm.id.get()) * 100 + "%");
        binding.textViewVar.setText(vm.category.get());
    }

    private void init() {
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = new ViewModelProvider(this).get(ResultViewModel.class);
    }

    private void setTheme() {
        SharedPreferences sp = App.sp;
        setTheme(App.setMyTheme(sp.getInt(SettingFragment.THEME, 20)));
    }


}