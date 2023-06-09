package com.example.todo_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todo_application.databinding.ActivityInsertDataBinding;

import java.util.Date;

public class InsertDataActivity extends AppCompatActivity {

    ActivityInsertDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");


        if (type.equals("edit")) {
            setTitle("edit");
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.description.setText(getIntent().getStringExtra("details"));
            int id = getIntent().getIntExtra("id",0);
            binding.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("details", binding.description.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            setTitle("Add Mode");
            binding.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("details", binding.description.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InsertDataActivity.this,MainActivity.class));
    }
}