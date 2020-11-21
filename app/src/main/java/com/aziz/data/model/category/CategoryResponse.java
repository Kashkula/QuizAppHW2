package com.aziz.data.model.category;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("trivia_categories")
    private ArrayList<CategoryModel> list;

    public ArrayList<CategoryModel> getList() {
        return list;
    }

    public void setList(ArrayList<CategoryModel> list) {
        this.list = list;
    }
}
