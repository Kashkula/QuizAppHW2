package com.aziz.ui.fragment.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    protected SettingViewModel vm;
    protected SettingFragmentBinding binding;
    protected ArrayList<ThemeModel> list;
    protected ThemeAdapter adapter;
    public static final String THEME = "theme";
    public static final String SHARED = "shared";
    protected String[] massiv = {"Red",
            "Orange",
            "Blue",
            "Dark",
            "Green",
            "Light"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SettingFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = ViewModelProviders.of(this).get(SettingViewModel.class);

        vm.defaultTheme();

        init();

        add(adapter);
        onClick();
    }

    public void onClick() {
        binding.layout4.setOnClickListener(v -> clear());
        binding.tvClear.setOnClickListener(v -> clear());
    }

    private void init() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        list = new ArrayList<>();
        adapter = new ThemeAdapter(list, this, Objects.requireNonNull(getActivity()));
        binding.rv.setLayoutManager(manager);
        binding.rv.setAdapter(adapter);
    }

    @Override
    public void openFragment(int position) {
        setTheme(position);
    }

    public void setTheme(int position) {
        setTheme(getContext(), position);
    }

    public void add(ThemeAdapter adapter) {
        for (String s : massiv) {
            adapter.add(new ThemeModel(s, R.drawable.phone));
        }
    }


    public void setTheme(Context context, int position) {
        SharedPreferences sP = App.sp;
        if (sP.getInt(SettingFragment.THEME, 20) != position) {
            sP.edit().putInt(THEME, position).apply();
            Objects.requireNonNull(context).startActivity(new Intent(context, SplashActivity.class));
            getActivity().finish();
        } else {
            Toast.makeText(context, "Вы уже выбрали эту тему!", Toast.LENGTH_SHORT).show();
        }
    }


    public void clear() {
        Toast.makeText(getContext(), "Вы очистили историю!", Toast.LENGTH_SHORT).show();
        App.repository.deleteAll();
    }

}