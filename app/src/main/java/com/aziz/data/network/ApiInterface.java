package com.aziz.data.network;

import com.aziz.data.model.category.CategoryResponse;
import com.aziz.data.model.question.QuestionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api.php")
    Call<QuestionResponse> getQuestionModel(@Query("amount") int amount,
                                            @Query("category") int category,
                                            @Query("difficulty") String difficulty);

    @GET("api_category.php")
    Call<CategoryResponse> getCategories();
}
