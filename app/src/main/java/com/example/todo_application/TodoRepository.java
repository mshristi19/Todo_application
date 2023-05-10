package com.example.todo_application;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class TodoRepository {

    private TodoDao todoDao;
    private LiveData<List<Todo>> todolist;

    public TodoRepository(Application application)
    {
        TodoDatabase todoDatabase = TodoDatabase.getInstance(application);
        todoDao=todoDatabase.todoDao();
        todolist = todoDao.getAllData();
    }
    public void insertData(Todo todo){new InsertTask(todoDao).execute(todo);}
    public void updateData(Todo todo){new UpdateTask(todoDao).execute(todo);}
    public void deleteData(Todo todo){new DeleteTask(todoDao).execute(todo);}

    public LiveData<List<Todo>> getAllData() {
        return todolist;
    }

    private static class DeleteTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;

        public DeleteTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;

        public UpdateTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.update(todos[0]);
            return null;
        }
    }
    private static class InsertTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;

        public InsertTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }
}
