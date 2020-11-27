package com.aziz.data.adapter.theme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.adapter.base.BaseRecyclerViewAdapter;
import com.aziz.data.model.ThemeModel;
import com.aziz.databinding.ListQuestionBinding;
import com.aziz.databinding.ListThemeBinding;

import java.util.ArrayList;

public class ThemeAdapter extends BaseRecyclerViewAdapter<ThemeModel> {

    protected ListThemeBinding binding;
    protected OnClickSF listener;

    public ThemeAdapter(ArrayList<ThemeModel> list, OnClickSF listener) {
        super(list);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListThemeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    protected void bind(ThemeModel themeModel, RecyclerView.ViewHolder holder) {
        binding.tvTheme.setText(themeModel.getName());
        binding.ivTheme.setOnClickListener(v -> {
            listener.openFragment(holder.getAdapterPosition());
        });
    }

    public void add(ThemeModel model) {
        list.add(model);
        notifyDataSetChanged();
    }
}
