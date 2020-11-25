package com.aziz.data.network;

import com.aziz.data.model.category.CategoryResponse;
import com.aziz.data.model.question.QuestionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    public void getQuestionModel(final QuestionsCallBack callBack, int amount, int category, String difficulty) {
        Call<QuestionResponse> call = apiInterface.getQuestionModel(amount, category, difficulty);
        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().getResults());
                } else {
                    callBack.onFailure(new Exception("Response is empty" + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void getCategory(final CategoryCallBack callBack) {
        Call<CategoryResponse> call = apiInterface.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }
}
