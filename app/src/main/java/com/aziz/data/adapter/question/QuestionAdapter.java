package com.aziz.data.adapter.question;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.databinding.ListItemBinding;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    protected ArrayList<QuestionModel> list = new ArrayList<>();
    protected OnClickNextItemQA onClickNextItemQA;
    protected OnClickOpenActivity onClickOpenActivity;
    protected ListItemBinding binding;

    //kashkula

    public QuestionAdapter(OnClickNextItemQA onClickNextItemQA, OnClickOpenActivity onClickOpenActivity) {
        this.onClickNextItemQA = onClickNextItemQA;
        this.onClickOpenActivity = onClickOpenActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        //may be and so...
//        binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
//        binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding.setQm(list.get(position));
        binding.setVh(holder);
        holder.clear();
        holder.setBackground(list.get(holder.getAdapterPosition()).getBtnPosition());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.list = questions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void methods(String text, int position) {
            boolean knopka = false;
            QuestionModel qm = list.get(getAdapterPosition());
            if (qm.isKnopka() && text.equals(qm.getCorrectAnswer())) {
                qm.setKnopka(false);
                knopka = true;
            }

            setBackground(position);

            onClickNextItemQA.nextItem(knopka, getAdapterPosition());

            if (getAdapterPosition() == list.size() - 1)
                onClickOpenActivity.answersMethod();
        }

        private void setBackground(int positionBtn) {
            QuestionModel qm = binding.getQm();
            if (qm.getBtnPosition() == 100)
                qm.setBtnPosition(positionBtn);

            Button[] btns_multiple = {binding.btnFirst, binding.btnSecond, binding.btnThird, binding.btnFourth};
            Button[] btns_boolean = {binding.btnFifth, binding.btnSixth};

            switch (qm.getBtnPosition()) {
                case 1:
                    if (binding.btnFirst.getText().equals(qm.getCorrectAnswer())) {
                        binding.btnFirst.setBackgroundResource(R.drawable.for_true_variant);
                        binding.btnFifth.setBackgroundResource(R.drawable.for_true_variant);
                    } else {
                        correctAnswerByDefault();
                        binding.btnFirst.setBackgroundResource(R.drawable.for_false_variant);
                        binding.btnFifth.setBackgroundResource(R.drawable.for_false_variant);
                    }
                    binding.btnFirst.setTextColor(Color.WHITE);
                    binding.btnFifth.setTextColor(Color.WHITE);
                    break;

                case 2:
                    if (binding.btnSecond.getText().equals(qm.getCorrectAnswer())) {
                        binding.btnSecond.setBackgroundResource(R.drawable.for_true_variant);
                        binding.btnSixth.setBackgroundResource(R.drawable.for_true_variant);
                    } else {
                        correctAnswerByDefault();
                        binding.btnSecond.setBackgroundResource(R.drawable.for_false_variant);
                        binding.btnSixth.setBackgroundResource(R.drawable.for_false_variant);
                    }
                    binding.btnSecond.setTextColor(Color.WHITE);
                    binding.btnSixth.setTextColor(Color.WHITE);
                    break;

                case 3:
                    if (binding.btnThird.getText().equals(qm.getCorrectAnswer())) {
                        binding.btnThird.setBackgroundResource(R.drawable.for_true_variant);
                    } else {
                        correctAnswerByDefault();
                        binding.btnThird.setBackgroundResource(R.drawable.for_false_variant);
                    }
                    binding.btnThird.setTextColor(Color.WHITE);
                    break;
                case 4:
                    if (binding.btnFourth.getText().equals(qm.getCorrectAnswer())) {
                        binding.btnFourth.setBackgroundResource(R.drawable.for_true_variant);
                    } else {
                        correctAnswerByDefault();
                        binding.btnFourth.setBackgroundResource(R.drawable.for_false_variant);
                    }
                    binding.btnFourth.setTextColor(Color.WHITE);
                    break;
            }

        }

        void correctAnswerByDefault() {
            QuestionModel qm = binding.getQm();
            if
            (qm.getCorrectAnswer().equals(binding.getQm().getIncorrectAnswers().get(0))) {
                binding.btnFirst.setBackgroundResource(R.drawable.for_true_variant);
                binding.btnFifth.setBackgroundResource(R.drawable.for_true_variant);
                binding.btnFirst.setTextColor(Color.WHITE);
                binding.btnFifth.setTextColor(Color.WHITE);
            } else if
            (qm.getCorrectAnswer().equals(binding.getQm().getIncorrectAnswers().get(1))) {
                binding.btnSecond.setBackgroundResource(R.drawable.for_true_variant);
                binding.btnSecond.setTextColor(Color.WHITE);
                binding.btnSixth.setBackgroundResource(R.drawable.for_true_variant);
                binding.btnSixth.setTextColor(Color.WHITE);
            } else if
            (qm.getCorrectAnswer().equals(binding.getQm().getIncorrectAnswers().get(2))) {
                binding.btnThird.setBackgroundResource(R.drawable.for_true_variant);
                binding.btnThird.setTextColor(Color.WHITE);
            } else if
            (qm.getCorrectAnswer().equals(binding.getQm().getIncorrectAnswers().get(3))) {
                binding.btnFourth.setBackgroundResource(R.drawable.for_true_variant);
                binding.btnFourth.setTextColor(Color.WHITE);
            }

        }

        void clear() {
            binding.btnFirst.setBackgroundResource(R.drawable.for_variants);
            binding.btnSecond.setBackgroundResource(R.drawable.for_variants);
            binding.btnThird.setBackgroundResource(R.drawable.for_variants);
            binding.btnFourth.setBackgroundResource(R.drawable.for_variants);
            binding.btnFifth.setBackgroundResource(R.drawable.for_variants);
            binding.btnSixth.setBackgroundResource(R.drawable.for_variants);

            binding.btnFirst.setTextColor(R.style.ForBtn);
            binding.btnSecond.setTextColor(R.style.ForBtn);
            binding.btnThird.setTextColor(R.style.ForBtn);
            binding.btnFourth.setTextColor(R.style.ForBtn);
            binding.btnFifth.setTextColor(R.style.ForBtn);
            binding.btnSixth.setTextColor(R.style.ForBtn);
        }
    }
}
