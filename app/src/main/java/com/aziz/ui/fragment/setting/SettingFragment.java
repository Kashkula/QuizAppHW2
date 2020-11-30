package com.aziz.ui.fragment.setting;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aziz.App;
import com.aziz.R;
import com.aziz.data.adapter.theme.OnClickSF;
import com.aziz.data.adapter.theme.ThemeAdapter;
import com.aziz.data.model.ThemeModel;
import com.aziz.databinding.SettingFragmentBinding;
import com.aziz.ui.activity.SplashActivity;

import java.util.ArrayList;
import java.util.Objects;

public class SettingFragment extends Fragment implements OnClickSF {

    private SettingViewModel vm;
    protected SettingFragmentBinding binding;
    protected ArrayList<ThemeModel> list;
    protected ThemeAdapter adapter;
    public static final String THEME = "theme";
    public static final String SHARED = "shared";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(this).get(SettingViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sP = App.sp;
        sP.edit().putInt(THEME, 5).apply();

        init();

        add("Red");
        add("Orange");
        add("Blue");
        add("Dark");
        add("Green");
        add("Light");

        binding.layout4.setOnClickListener(v -> {
            clear();
        });

        binding.tvClear.setOnClickListener(v -> {
            clear();
        });

    }

    private void init() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        list = new ArrayList<>();
        adapter = new ThemeAdapter(list, this, Objects.requireNonNull(getActivity()));
        binding.rv.setLayoutManager(manager);
        binding.rv.setAdapter(adapter);

    }

    public void clear() {
        Toast.makeText(getContext(), "History: cleaned", Toast.LENGTH_SHORT).show();
        App.db.quizDao().deleteAll();
    }

    public void add(String name) {
        adapter.add(new ThemeModel(name, R.drawable.phone));
    }

    @Override
    public void openFragment(int position) {
        setTheme(position);
    }

    public void setTheme(int position) {
        SharedPreferences sP = App.sp;
        if (sP.getInt(SettingFragment.THEME, 20) != position) {
            sP.edit().putInt(THEME, position).apply();
            Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), SplashActivity.class));
            getActivity().finish();
        } else
            Toast.makeText(getContext(), "Вы уже выбрали эту тему!", Toast.LENGTH_SHORT).show();
    }
}