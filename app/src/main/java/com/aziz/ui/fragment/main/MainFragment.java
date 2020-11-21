package com.aziz.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.aziz.R;
import com.aziz.data.model.category.CategoryModel;
import com.aziz.databinding.MainFragmentBinding;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    protected MainFragmentBinding binding;
    public static final String ID = "id";
    public static final String CATEGORY = "category";
    public static final String DIFFICULTY = "difficulty";


    public static MainFragment newInstance() {
        return new MainFragment();
    }

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
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        liveData();
        seekBarListener();
    }

    private void liveData() {
        mViewModel.getCategories();
        mViewModel.fS_mutable.observe(getViewLifecycleOwner(), new Observer<ArrayList<CategoryModel>>() {
            @Override
            public void onChanged(ArrayList<CategoryModel> categoryModels) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mViewModel.fS_list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerFirst.setAdapter(adapter);
            }
        });
        mViewModel.name.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvTen.setText(s);
                binding.seekBar.setProgress(Integer.parseInt(s));
            }
        });
    }


    private void init() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setMv(mViewModel);
    }

    private void onClick() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.openActivity(getView(),
                        binding.seekBar.getProgress(),
                        Objects.requireNonNull(mViewModel.fS_mutable.getValue()).get(binding.spinnerFirst.getSelectedItemPosition()).getId(),
                        binding.spinnerSecond.getSelectedItem().toString().toLowerCase());
            }
        });
    }

    private void seekBarListener() {
        binding.seekBar.setOnSeekBarChangeListener(new IOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.tvTen.setText(String.valueOf(progress));
                mViewModel.num = progress;
            }
        });
    }
}