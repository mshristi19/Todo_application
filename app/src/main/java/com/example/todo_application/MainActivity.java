package com.example.todo_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import com.example.todo_application.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        todoViewModel=new ViewModelProvider(this,(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(TodoViewModel.class);
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsertDataActivity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        TodoAdaptor adaptor = new TodoAdaptor(MainActivity.this);
        binding.recyclerView.setAdapter(adaptor);

        todoViewModel.getAllTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adaptor.submitList(todos);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT){
                    todoViewModel.delete(adaptor.getTodo(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this,"todo item deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, InsertDataActivity.class);
                    intent.putExtra("type","edit");
                    intent.putExtra("title",adaptor.getTodo(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("details",adaptor.getTodo(viewHolder.getAdapterPosition()).getDetails());
                    intent.putExtra("id",adaptor.getTodo(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);
                }
            }
        }).attachToRecyclerView(binding.recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String title = data.getStringExtra("title");
            String details = data.getStringExtra("details");
            Todo todo = new Todo(title,details);
            todoViewModel.insert(todo);
            Toast.makeText(this,"Added to the list",Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==2)
        {
            String title = data.getStringExtra("title");
            String details = data.getStringExtra("details");
            Todo todo = new Todo(title,details);
            todo.setId(data.getIntExtra("id",0));
            todoViewModel.update(todo);
            Toast.makeText(this,"todo list updated",Toast.LENGTH_SHORT).show();
        }
    }

}