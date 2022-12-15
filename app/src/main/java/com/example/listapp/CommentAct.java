package com.example.listapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.listapp.databinding.ActivityComment2Binding;

public class CommentAct extends AppCompatActivity implements View.OnClickListener {

    ActivityComment2Binding binding;

    private Button exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComment2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.exit.setOnClickListener(this);
        Intent intent = getIntent();
        Comments comments = (Comments) intent.getSerializableExtra("com");
        binding.titleOfCommentActivity.setText(comments.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comments:
                finish();
                break;
            case R.id.exit:
                Intent intent = new Intent(CommentAct.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}