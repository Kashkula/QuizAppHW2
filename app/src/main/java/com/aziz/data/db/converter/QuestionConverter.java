package com.aziz.data.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.aziz.data.model.question.QuestionModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionConverter {

    @TypeConverter
    public static String toRaw(@Nullable ArrayList<QuestionModel> questionModels) {
        if (questionModels == null) return null;

        Gson gson = new Gson();
        return gson.toJson(questionModels);
    }

    @TypeConverter
    public static ArrayList<QuestionModel> fromRaw(@Nullable String model) {
        if (model == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QuestionModel>>() {
        }.getType();
        return gson.fromJson(model, type);
    }
}
