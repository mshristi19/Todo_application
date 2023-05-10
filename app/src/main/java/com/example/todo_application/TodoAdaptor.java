package com.example.todo_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_application.databinding.EachItemBinding;

import javax.security.auth.callback.Callback;

public class TodoAdaptor extends ListAdapter<Todo,TodoAdaptor.ViewHolder> {
    public TodoAdaptor(){
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Todo> CALLBACK=new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDetails().equals(newItem.getDetails());
        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Todo todo = getItem(position);
        holder.binding.title.setText(todo.getTitle());
        holder.binding.description.setText(todo.getDetails());
    }

    public Todo getTodo(int position)
    {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EachItemBinding binding;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            binding=EachItemBinding.bind(itemView);

        }
    }
}
