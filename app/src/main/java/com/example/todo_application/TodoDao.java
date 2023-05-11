package com.example.todo_application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@SuppressWarnings("ALL")
@Dao
public interface TodoDao {

    @Insert
    public void insert(Todo todo);
    @Update
    public void update(Todo todo);
    @Delete
    public void delete(Todo todo);

    @Query("SELECT * FROM todo_list")
    public LiveData<List<Todo>> getAllData();
}
