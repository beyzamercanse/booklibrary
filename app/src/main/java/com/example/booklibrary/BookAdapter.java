package com.example.booklibrary;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklibrary.databinding.ActivityBooksBinding;
import com.example.booklibrary.models.BookModel;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final List<BookModel> data;

    public BookAdapter(List<BookModel> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityBooksBinding = ActivityBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ActivityBooksBinding binding = null;
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final ActivityBooksBinding binding;

        public ViewHolder(ActivityBooksBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
