package com.aziz.data.model;

import com.aziz.data.model.question.QuestionModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class QuizResult implements Serializable {

    private String category;
    private String difficulty;
    private int correctAnswerResult;
    private Date createdAt;
    private ArrayList<QuestionModel> list;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswerResult() {
        return correctAnswerResult;
    }

    public void setCorrectAnswerResult(int correctAnswerResult) {
        this.correctAnswerResult = correctAnswerResult;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<QuestionModel> getList() {
        return list;
    }

    public void setList(ArrayList<QuestionModel> list) {
        this.list = list;
    }

    public QuizResult(String category, String difficulty) {
        this.category = category;
        this.difficulty = difficulty;
    }

    public QuizResult(String category, String difficulty, int correctAnswerResult, Date createdAt, ArrayList<QuestionModel> list, int amount) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.createdAt = createdAt;
        this.list = list;
        this.amount = amount;
    }
}
