package com.aziz.data.model.question;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionResponse {

    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("results")
    private ArrayList<QuestionModel> results;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<QuestionModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<QuestionModel> results) {
        this.results = results;
    }
}
