package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.FileUtils;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import com.example.booklibrary.databinding.ActivityMainBinding;
import com.example.booklibrary.models.BookModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSave.setOnClickListener(v -> save());
        binding.buttonLoad.setOnClickListener(v -> load());

        try {
            String book = readFromAssets("abook.json");
            JSONObject bookItem = new JSONObject(book);
            String text = "재목: " + bookItem.getString("title") +
                    ", 가격: " + bookItem.getInt("price") + "\n" + "책 이미지 url: " + bookItem.getString("image");
            binding.editMemo.setText(text);
        } catch (IOException e) {
            Log.i("DEBUG", "파일 읽기 실패");
        } catch (JSONException e) {
            Log.i("DEBUG", "JSON 파일 에러");
        }
    }

    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }


    public void save() {
        // TODO HERE
        try {
            FileOutputStream fos = openFileOutput("memo.txt", Context.MODE_PRIVATE);
            fos.write(binding.editMemo.getText().toString().getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        String jsonString = FileUtils.readFromAssets(this, "abook.json");
        Log.i("JSON", jsonString);

        Gson gson = new Gson();
        BookModel book = gson.fromJson(jsonString, BookModel.class);
        String title = book.getTitle();
        binding.editMemo.setText(title);
    }



    public static String readFile(Context context, String filename) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        String contents = "";
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // error occurred while opening a raw file for reading.
        } finally {
            contents = stringBuilder.toString().trim();
        }
        return contents;
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

}


    private void writeFile(String filename, String data) {
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(data.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        String contents = binding.editMemo.getText().toString();
        FileUtils.writeFile(this, "memo.txt", contents);
    }

    private void load(String filename) {
        try {
            String loadedContents = FileUtils.readFile(filename);
            binding.editMemo.setText(loadedContents);
        } catch (FileNotFoundException e ) {
        }

    }

    private String readFile(String filename) throws FileNotFoundException {
        FileInputStream fis = openFileInput(filename);

        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // error occurred while opening a raw file for reading.
        }
        return StringBuilder.toString().trim();
    }

    public static void writeFile(Context context, String filename, String data){
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
