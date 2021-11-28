package com.example.booklibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.booklibrary.utils.FileUtils
import com.example.booklibrary.models.BookModel;

public class BookActivity extends AppCompatActivity {
    private ActivityBookBinding binding;
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String booksJson = FileUtils.readFromAssets(this, "books.json");
        List<BookModel> array = gson.fromJson, new TypeToken<List<BookModel>>() {
        }.getType());
        BookAdapter textAdapter = new BookAdapter(array);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(textAdapter);
    }
}
