package com.example.todo_application;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo_list")
public class Todo {
    private String title;
    private String details;

    private Date data;

    @PrimaryKey(autoGenerate=true)
    private int id=0;


    @Ignore
    public Todo(String title, String details, Date data, int id) {
        this.title = title;
        this.details = details;
        this.data = data;
        this.id = id;
    }

    public Todo(String title, String details, Date data) {
        this.title = title;
        this.details = details;
        this.data = data;;
    }

    @Ignore
    public Todo() {

    }

    public Todo(String tmpTitle, String tmpDetails, Date tmpData, boolean tmpIsComplete) {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public boolean isComplete() {
        return false;
    }
}
