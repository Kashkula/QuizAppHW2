package com.aziz.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aziz.App;
import com.aziz.R;
import com.aziz.data.adapter.pager.FragmentAdapter;
import com.aziz.ui.activity.question.QuestionActivity;
import com.aziz.ui.fragment.history.HistoryFragment;
import com.aziz.ui.fragment.main.MainFragment;
import com.aziz.ui.fragment.main.OnClickListenerMF;
import com.aziz.ui.fragment.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListenerMF {

    private BottomNavigationView bottomNavigationView;
    protected ViewPager viewPager;
    protected List<Fragment> list;
    HistoryFragment historyFragment = new HistoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(SettingFragment.SHARED,MODE_PRIVATE);
        setMyTheme(sp.getInt(SettingFragment.THEME, 20));

        setContentView(R.layout.main_activity);
        init();
        fillFragment();
        bottomNavView();
        setPageAdapter();
    }

    private void setPageAdapter() {
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
        viewPager.setOffscreenPageLimit(3);
    }

    private void fillFragment() {
        if (list != null) {
            list.add(new MainFragment());
            list.add(historyFragment);
            list.add(new SettingFragment());
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void bottomNavView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_main:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.menu_history:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.menu_settings:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }


    private void init() {
        list = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void openActivity(int id, int category, String categoryStr, String difficulty) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(MainFragment.ID, id);
        intent.putExtra(MainFragment.CATEGORY, category);
        intent.putExtra(MainFragment.DIFFICULTY, difficulty);
        intent.putExtra(MainFragment.CATEGORY_STR, categoryStr);
        startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    public void setMyTheme(int theme) {
        switch (theme) {
            case 0:
                setTheme(R.style.Red);
                break;
            case 1:
                setTheme(R.style.Orange);
                break;
            case 2:
                setTheme(R.style.Blue);
                break;
            case 3:
                setTheme(R.style.Dark);
                break;
            case 4:
                setTheme(R.style.Green);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }
    }


//    private void openMainF(Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commit();
//        }
//    }
}