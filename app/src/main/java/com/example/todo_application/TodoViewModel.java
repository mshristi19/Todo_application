package com.example.todo_application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository todoRepository;
    private LiveData<List<Todo>> todolist;
    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoRepository=new TodoRepository(application);
        todolist=todoRepository.getAllData();
    }

    public void insert (Todo todo){
        todoRepository.insertData(todo);
    }
    public void delete(Todo todo){
        todoRepository.deleteData(todo);
    }

    public void edit(Todo todo){todoRepository.updateData(todo);}

    public LiveData<List<Todo>> getAllTodos(){
        return todolist;
    }

}
