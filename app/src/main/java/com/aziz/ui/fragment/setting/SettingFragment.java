package com.aziz.ui.fragment.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.adapter.theme.OnClickSF;
import com.aziz.data.adapter.theme.ThemeAdapter;
import com.aziz.data.model.ThemeModel;
import com.aziz.databinding.SettingFragmentBinding;

import java.util.ArrayList;
import java.util.Objects;

public class SettingFragment extends Fragment implements OnClickSF {

    protected SettingViewModel vm;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = ViewModelProviders.of(this).get(SettingViewModel.class);

        vm.defaultTheme();

        init();

        vm.addList(adapter);
        onClick();
    }

    public void onClick() {
        binding.layout4.setOnClickListener(v -> vm.clear(getContext()));
        binding.tvClear.setOnClickListener(v -> vm.clear(getContext()));
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
        vm.setTheme(getContext(), position);
        vm.booleanMutableLiveData.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                Objects.requireNonNull(getActivity()).finish();
        });
    }
}