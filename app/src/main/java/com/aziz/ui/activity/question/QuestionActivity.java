package com.aziz.ui.activity.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.aziz.R;
import com.aziz.data.adapter.question.OnClickNextItemQA;
import com.aziz.data.adapter.question.OnClickOpenActivity;
import com.aziz.data.adapter.question.QuestionAdapter;
import com.aziz.data.custom.CustomLinearLayoutManager;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.databinding.ActivityQuestionBinding;
import com.aziz.ui.activity.ResultActivity;
import com.aziz.ui.fragment.main.MainFragment;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements OnClickNextItemQA, OnClickOpenActivity {
    protected ActivityQuestionBinding binding;
    protected QuestionAdapter adapter;
    protected QuestionViewModel viewModel;
    protected int position;
    protected String difficulty;
    protected int id, category;
    protected int forAnswer = 0;
    public final static String CORRECT_ANSWER = "correct_answer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getFromIntent();
        getQuestions();
        setBinding();

        onBack();

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        binding.setQv(viewModel);

        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);

        adapter = new QuestionAdapter(this, this);

        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(layoutManager);
    }

    public void getQuestions() {
        //get questions
        viewModel.getQuestion(id, category, difficulty);
        viewModel.questionLiveData.observe(this, new Observer<ArrayList<QuestionModel>>() {
            @Override
            public void onChanged(ArrayList<QuestionModel> questionModels) {
                adapter.setQuestions(questionModels);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setBinding() {
        binding.pBar.setMax(id);
        viewModel.pBar_number.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvQuality.setText(integer + "/" + binding.pBar.getMax());
                binding.pBar.setProgress(integer);
            }
        });
    }

    private void getFromIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(MainFragment.ID, 22);
        category = intent.getIntExtra(MainFragment.CATEGORY, 23);
        difficulty = intent.getStringExtra(MainFragment.DIFFICULTY);
    }

    @Override
    public void nextItem(boolean correctAnswer, final int adapterPosition) {
        if (correctAnswer)
            forAnswer++;
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                position = adapterPosition + 1;
                binding.pBar.setProgress(position);
                viewModel.pBar_number.setValue(position);
                binding.rv.scrollToPosition(position);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (position > 0) {
            binding.rv.scrollToPosition(position -= 1);
            viewModel.setpBar_number(position);
        } else
            super.onBackPressed();
    }

    public void onBack() {
        binding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void answersMethod() {
        QuestionModel model = viewModel.qm_list.get(0);
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra(MainFragment.CATEGORY, model.getCategory());
        intent.putExtra(MainFragment.ID, id);
        intent.putExtra(MainFragment.DIFFICULTY, difficulty);
        intent.putExtra(CORRECT_ANSWER, forAnswer);
        startActivity(intent);
        finish();

    }
}