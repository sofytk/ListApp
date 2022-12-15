package com.example.listapp;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Comments implements Serializable {
    private String title;
    Comments(String title){
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return "Комментарии под постом: " + title;
    }
}
