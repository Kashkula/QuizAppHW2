package com.aziz.data.adapter.theme;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.adapter.base.BaseRecyclerViewAdapter;
import com.aziz.data.model.ThemeModel;
import com.aziz.databinding.ListQuestionBinding;
import com.aziz.databinding.ListThemeBinding;
import com.aziz.ui.fragment.setting.SettingFragment;

import java.util.ArrayList;

public class ThemeAdapter extends BaseRecyclerViewAdapter<ThemeModel> {

    protected ListThemeBinding binding;
    protected OnClickSF listener;
    protected int position = -1;
    protected SharedPreferences sp;

    public ThemeAdapter(ArrayList<ThemeModel> list, OnClickSF listener, FragmentActivity activity) {
        super(list);
        this.listener = listener;
        sp = activity.getPreferences(Context.MODE_PRIVATE);
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
            onClick(holder.getAdapterPosition());
        });
        binding.rb.setOnClickListener(v -> {
            onClick(holder.getAdapterPosition());
        });
        position = sp.getInt(SettingFragment.THEME, 20);

        if (holder.getAdapterPosition() == position) {
            binding.rb.setChecked(holder.getAdapterPosition() == position);
        }
    }

    public void add(ThemeModel model) {
        list.add(model);
        notifyDataSetChanged();
    }

    public void onClick(int position) {
        this.position = position;
        listener.openFragment(position);
    }
}
