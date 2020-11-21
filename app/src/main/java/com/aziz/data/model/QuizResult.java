package com.aziz.data.model;

import com.aziz.data.model.question.QuestionModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class QuizResult implements Serializable{

    private String category;
    private String difficulty;
    private int correctAnswerResult;
    private Date createdAt;
    private ArrayList<QuestionModel> listt;

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

    public ArrayList<QuestionModel> getListt() {
        return listt;
    }

    public void setListt(ArrayList<QuestionModel> listt) {
        this.listt = listt;
    }

    public QuizResult(String category, String difficulty, ArrayList<QuestionModel> listt) {
        this.category = category;
        this.difficulty = difficulty;
        this.listt = listt;
    }

    public QuizResult(String category, String difficulty, int correctAnswerResult, Date createdAt, ArrayList<QuestionModel> listt) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.createdAt = createdAt;
        this.listt = listt;
    }
}
