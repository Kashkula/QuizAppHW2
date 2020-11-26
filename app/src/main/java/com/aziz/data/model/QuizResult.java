package com.aziz.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.aziz.data.db.converter.DateConverter;
import com.aziz.data.db.converter.QuestionConverter;
import com.aziz.data.model.question.QuestionModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    public
    int id;
    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "correctAnswerResult")
    private int correctAnswerResult;

    @TypeConverters(DateConverter.class)
    private Date createdAt;

    @TypeConverters(QuestionConverter.class)
    private ArrayList<QuestionModel> list;

    @ColumnInfo(name = "amount")
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

    public QuizResult(String category, String difficulty, int correctAnswerResult, Date createdAt, ArrayList<QuestionModel> list, int amount) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.createdAt = createdAt;
        this.list = list;
        this.amount = amount;
    }
}
