package com.aziz.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aziz.R;
import com.aziz.data.adapter.pager.FragmentAdapter;
import com.aziz.data.model.QuizResult;
import com.aziz.ui.activity.question.QuestionActivity;
import com.aziz.ui.activity.question.QuestionViewModel;
import com.aziz.ui.fragment.history.HistoryFragment;
import com.aziz.ui.fragment.main.MainFragment;
import com.aziz.ui.fragment.main.OnClickListenerMF;
import com.aziz.ui.fragment.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
        startActivityForResult(intent, 22);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == RESULT_OK && data != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<QuizResult>() {
            }.getType();
            QuizResult qr = gson.fromJson(data.getStringExtra(QuestionViewModel.QUIZ_RESULT), type);
            historyFragment.listAdd(qr);
            Log.d("TAG", "startActivityForResult: " + qr.getDifficulty());
        }

    }

    //    @Override
//    public void openActivity() {
//        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
//    }


//    private void openMainF(Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commit();
//        }
//    }
}