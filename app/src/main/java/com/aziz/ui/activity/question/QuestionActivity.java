package com.aziz.ui.activity.question;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.aziz.R;
import com.aziz.data.adapter.question.OnClickNextItemQA;
import com.aziz.data.adapter.question.OnClickOpenActivity;
import com.aziz.data.adapter.question.QuestionAdapter;
import com.aziz.data.custom.CustomLinearLayoutManager;
import com.aziz.databinding.ActivityQuestionBinding;

public class QuestionActivity extends AppCompatActivity implements OnClickNextItemQA, OnClickOpenActivity {
    protected ActivityQuestionBinding binding;
    protected QuestionAdapter adapter;
    protected QuestionViewModel vm;
    public final static String CORRECT_ANSWER = "correct_answer";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        methodsVM();
        onBack();
        onSkip();
        methodsVM();
        binding.tvQuality.setText(0 + "/" + vm.id);
    }

    private void methodsVM() {
        vm.getFromIntent(getIntent());
        vm.beginPBar(binding.pBar, binding.tvQuality);
        vm.getQuestionLiveData(this, adapter);
    }

    private void onSkip() {
        binding.btnSkip.setOnClickListener(v -> vm.onSkip(binding.rv));
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
        vm.nextItem(correctAnswer, adapterPosition, binding.rv);
    }

    @Override
    public void onBackPressed() {
        vm.outback(this, binding.rv);
    }

    public void onBack() {
        binding.ibBack.setOnClickListener(v -> finish());
    }

    @Override
    public void answersMethod() {
        vm.sendIntent(this);
    }
}