package com.aziz.ui.fragment.setting;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aziz.App;
import com.aziz.R;
import com.aziz.ui.fragment.history.HistoryFragment;

public class SettingFragment extends Fragment {

    private SettingViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.layout_4).setOnClickListener(v -> {
            clear();
        });

        view.findViewById(R.id.tv_clear).setOnClickListener(v -> {
            clear();
        });

    }

    public void clear() {
        Toast.makeText(getContext(), "History: cleaned", Toast.LENGTH_SHORT).show();
        App.db.quizDao().deleteAll();
    }
}