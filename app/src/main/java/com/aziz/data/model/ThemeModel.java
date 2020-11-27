package com.aziz.data.model;

public class ThemeModel {
    private String name;
    private int src;

    public ThemeModel(String name, int src) {
        this.name = name;
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public int getSrc() {
        return src;
    }
}
