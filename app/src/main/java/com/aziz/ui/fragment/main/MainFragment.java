package com.aziz.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.aziz.R;
import com.aziz.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel vm;
    protected MainFragmentBinding binding;
    public static final String ID = "id";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_STR = "categoryStr";
    public static final String DIFFICULTY = "difficulty";
    protected OnClickListenerMF listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        onClick();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(MainViewModel.class);
        liveData();
        seekBarListener();
    }

    private void liveData() {
        vm.getCategories();
        vm.fS_mutable.observe(getViewLifecycleOwner(), categoryModels -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, vm.fS_list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerFirst.setAdapter(adapter);
        });
        vm.name.observe(getViewLifecycleOwner(), s -> {
            binding.tvTen.setText(s);
            binding.seekBar.setProgress(Integer.parseInt(s));
        });
    }

    private void init() {
        vm = ViewModelProviders.of(this).get(MainViewModel.class);
        listener = (OnClickListenerMF) requireContext();
        binding.setMv(vm);
    }

    private void onClick() {
        binding.btnStart.setOnClickListener(v ->
                listener.openActivity(binding.seekBar.getProgress(),
                        vm.fS_mutable.getValue().get(binding.spinnerFirst.getSelectedItemPosition()).getId(),
                        vm.fS_mutable.getValue().get(binding.spinnerFirst.getSelectedItemPosition()).getName(),
                        binding.spinnerSecond.getSelectedItem().toString().toLowerCase()));
    }

    private void seekBarListener() {
        binding.seekBar.setOnSeekBarChangeListener(new IOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.tvTen.setText(String.valueOf(progress));
                vm.num = progress;
            }
        });
    }
}