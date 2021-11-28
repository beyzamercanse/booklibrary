package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.FileUtils;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;
import com.example.booklibrary.utils.FileUtils

import com.bumptech.glide.Glide;
import com.example.booklibrary.databinding.ActivityJsonBinding;
import com.example.booklibrary.models.BookModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class JSONActivity extends AppCompatActivity{
    private ActivityJsonBinding binding;
    private BookModel item;

    private final Gson gson = new Gson();
    private final String BOOK_FILENAME = "abook.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJsonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textPrint.setMovementMethod(new ScrollingMovementMethod());
        binding.textPrint.setMovementMethod(LinkMovementMethod.getInstance());

        File book = new File(getFilesDir(), BOOK_FILENAME);
        if (!book.exists()) {
            String bookJson = FileUtils.readFromAssets(this, "abook.json");
            FileUtils.writeFile(this, BOOK_FILENAME, bookJson);
        }
        load();
        binding.buttonSave.setOnClickListener(v -> save());
    }

    public void save() {
        String comments = binding.editComment.getText().toString();
        this.item.setComments(comments);
        String jsonString = gson.toJson(this.item);
        FileUtils.writeFile(this, "abook.json", jsonString);
    }

    public void load() {
        String bookJson = FileUtils.readFile(this, "abook.json");
        this.item = gson.fromJson(bookJson, BookModel.class);
        updateIU();
    }

    public void updateIU() {
        String text = "재목: " + item.getTitle() + ", 가격: " + item.getPrice();
        binding.textPrint.setText(text);
        binding.textTitle.setText(item.getTitle());
        binding.textAuthor.setText(item.getAuthor());
        binding.textPrice.setText(String.format("%,d원", item.getPrice()));
        binding.textDescription.setText(Html.fromHtml(item, getDescription()));
        binding.editComment.setText(item.getComments());

        Glide.with(this)
                .load(item.getImage())
                .into(binding.imageBook);

    }
}
