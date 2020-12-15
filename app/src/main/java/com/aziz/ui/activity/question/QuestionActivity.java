package com.aziz.ui.activity.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.App;
import com.aziz.R;
import com.aziz.data.adapter.question.OnClickNextItemQA;
import com.aziz.data.adapter.question.OnClickOpenActivity;
import com.aziz.data.adapter.question.QuestionAdapter;
import com.aziz.data.custom.CustomLinearLayoutManager;
import com.aziz.data.model.QuizResult;
import com.aziz.databinding.ActivityQuestionBinding;
import com.aziz.ui.activity.result.ResultActivity;
import com.aziz.ui.fragment.main.MainFragment;
import com.aziz.ui.fragment.setting.SettingFragment;
import com.google.gson.Gson;

import java.util.Date;

public class QuestionActivity extends AppCompatActivity implements OnClickNextItemQA, OnClickOpenActivity {
    protected ActivityQuestionBinding binding;
    protected QuestionAdapter adapter;
    protected QuestionViewModel vm;
    public final static String CORRECT_ANSWER = "correct_answer";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySetTheme();
        init();
        methods();
    }

    private void mySetTheme() {
        SharedPreferences sp = App.sp;
        setTheme(App.setMyTheme(sp.getInt(SettingFragment.THEME, 20)));
    }


    @SuppressLint("SetTextI18n")
    private void methods() {
        binding.tvQuality.setText(0 + "/" + vm.id);
        getFromIntent(getIntent());
        beginPBar(binding.pBar, binding.tvQuality);
        getQuestionLiveData(this, adapter);
        onBack();
        onSkip();
    }

    private void getQuestionLiveData(QuestionActivity questionActivity, QuestionAdapter adapter) {
        vm.questionLiveData.observe(questionActivity, adapter::setQuestions);
    }

    @SuppressLint("SetTextI18n")
    private void beginPBar(SeekBar pBar, TextView tvQuality) {
        pBar.setMax(vm.id);
        tvQuality.setText(0 + "/" + pBar.getMax());
    }

    private void getFromIntent(Intent intent) {
        vm.id = intent.getIntExtra(MainFragment.ID, 22);
        vm.category = intent.getIntExtra(MainFragment.CATEGORY, 23);
        vm.difficulty = intent.getStringExtra(MainFragment.DIFFICULTY);
        vm.categoryStr = intent.getStringExtra(MainFragment.CATEGORY_STR);
        vm.getQuestion();
    }

    private void onSkip() {
        binding.btnSkip.setOnClickListener(v -> {
            if (vm.position < vm.id)
                binding.rv.scrollToPosition(vm.position += 1);
        });
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        vm = new ViewModelProvider(this).get(QuestionViewModel.class);
        binding.setQv(vm);

        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);

        adapter = new QuestionAdapter(this, this);

        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(layoutManager);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void nextItem(boolean correctAnswer, final int adapterPosition) {
        binding.setPosition(adapterPosition + 1);
        binding.tvQuality.setText(adapterPosition + 1 + "/" + vm.id);
        nextItem(correctAnswer, adapterPosition, binding.rv);
    }

    @Override
    public void onBackPressed() {
        if (vm.position > 0)
            binding.rv.scrollToPosition(vm.position -= 1);
        else finish();
    }

    public void onBack() {
        binding.ibBack.setOnClickListener(v -> finish());
    }

    @Override
    public void answersMethod() {
        sendIntent();
    }

    private void sendIntent() {
        QuizResult quizResult = new QuizResult(vm.categoryStr, vm.difficulty, vm.forAnswer, new Date(System.currentTimeMillis()), vm.qm_list, vm.id);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(QuestionViewModel.QUIZ_RESULT, new Gson().toJson(quizResult));
        setResult(RESULT_OK, intent);
        startActivity(intent);
        finish();
    }


    public void nextItem(boolean correctAnswer, final int adapterPosition,
                         final RecyclerView rv) {
        if (correctAnswer)
            vm.forAnswer++;
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                vm.position = adapterPosition + 1;
                rv.scrollToPosition(vm.position);
            }
        }.start();
    }

}