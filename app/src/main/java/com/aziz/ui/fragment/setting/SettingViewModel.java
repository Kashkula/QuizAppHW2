package com.aziz.ui.fragment.setting;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aziz.App;
import com.aziz.R;
import com.aziz.data.adapter.theme.ThemeAdapter;
import com.aziz.data.model.ThemeModel;
import com.aziz.ui.activity.SplashActivity;

import java.util.Objects;

public class SettingViewModel extends ViewModel {

    public static final String THEME = "theme";

    public MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();


    public void defaultTheme() {
        SharedPreferences sP = App.sp;
        if (sP.getInt(SettingFragment.THEME, 20) == 5)
            sP.edit().putInt(SettingFragment.THEME, 5).apply();
    }


}