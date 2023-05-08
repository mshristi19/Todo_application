package com.example.todo_application;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_list")
public class Todo {
    private String title;
    private String details;

    @PrimaryKey(autoGenerate=true)
    private int id;

    public Todo(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
