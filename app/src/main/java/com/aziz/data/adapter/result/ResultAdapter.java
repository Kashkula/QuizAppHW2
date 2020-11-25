package com.aziz.data.adapter.result;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.adapter.base.BaseRecyclerViewAdapter;
import com.aziz.data.model.QuizResult;
import com.aziz.databinding.ListResultBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ResultAdapter extends BaseRecyclerViewAdapter<QuizResult> {

    protected ListResultBinding binding;

    public ResultAdapter(ArrayList<QuizResult> list) {
        super(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bind(QuizResult quizResult, RecyclerView.ViewHolder holder) {
        binding.setQr(quizResult);
        binding.tvCategory.setText("Category:" + quizResult.getCategory());
        binding.tvCorrect.setText("Correct answer: " + quizResult.getCorrectAnswerResult() + "/" + quizResult.getAmount());
        binding.tvDifficulty.setText("Difficulty: " + quizResult.getDifficulty());
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm");
        String strDate = dateFormat.format(quizResult.getCreatedAt());
        binding.tvDate.setText(strDate);
    }

    public void add(QuizResult quizResult) {
        list.add(quizResult);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
}
