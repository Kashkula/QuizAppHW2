package com.aziz.ui.fragment.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aziz.App;
import com.aziz.data.model.category.CategoryModel;
import com.aziz.data.network.IQuizApiClient;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<CategoryModel>> fS_mutable = new MutableLiveData<>();
    ArrayList<String> fS_list = new ArrayList<>();
    MutableLiveData<String> name = new MutableLiveData<>();
    Integer num = 1;


    public void increases() {
        if (num < 20)
            num++;
        setNum();
    }

    public void decreases() {
        if (num > 0)
            num--;
        setNum();
    }


    void setNum() {
        name.setValue(String.valueOf(num));
    }

    public void getCategories() {
        App.apiClient.getCategory(new IQuizApiClient.CategoryCallBack() {
            @Override
            public void onSuccess(ArrayList<CategoryModel> result) {
                for (int i = 0; i < result.size(); i++) {
                    fS_list.add(result.get(i).getName());
                }
                fS_mutable.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }
}