package com.aziz.data.network;

import com.aziz.data.model.category.CategoryModel;
import com.aziz.data.model.question.QuestionModel;

import java.util.ArrayList;

public interface IQuizApiClient {

    void getQuestionModel(QuestionsCallBack callBack, int amount, int category, String difficulty);

    void getCategory(CategoryCallBack callBack);

    interface QuestionsCallBack extends IBaseCallBack<ArrayList<QuestionModel>> {
        @Override
        void onSuccess(ArrayList<QuestionModel> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoryCallBack extends IBaseCallBack<ArrayList<CategoryModel>> {
        @Override
        void onSuccess(ArrayList<CategoryModel> result);

        @Override
        void onFailure(Exception e);
    }


}
