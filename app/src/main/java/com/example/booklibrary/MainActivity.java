package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;

import com.example.booklibrary.models.BookModel;
import com.example.booklibrary.utils.FileUtils;

import android.os.Bundle;

import android.text.Html;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


//import android.os.FileUtils;
import com.example.booklibrary.databinding.ActivityMainBinding;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
//            String book = readFromAssets("abook.json");
//            JSONObject bookItem = new JSONObject(book);
//            String text = "재목: " + bookItem.getString("title") +
//                    ", 가격: " + bookItem.getInt("price") + "\n" + "책 이미지 url: " + bookItem.getString("image");
//            binding.editMemo.setText(text);
//        } catch (IOException e) {
//            Log.i("DEBUG", "파일 읽기 실패");
//        } catch (JSONException e) {
//            Log.i("DEBUG", "JSON 파일 에러");
//        }
            String booksJson = readFromAssets("books.json");
            String bookJson = readFromAssets("abook.json");
            List<BookModel> array = gson.fromJson(booksJson, new TypeToken<List<BookModel>>() {
            }.getType());
            temp(bookJson);

        BookAdapter textAdapter = new BookAdapter(array);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(textAdapter);


        } catch (IOException e) {
            Log.i("DEBUG", "파일 읽기 실패나  json 파일의 에러 ");
        }
    }

    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }


    public void temp(String jsonString) {
        Gson gson = new Gson();

        BookModel item = gson.fromJson(jsonString, BookModel.class);
        binding.textAuthor.setText(item.getAuthor());
        binding.textTitle.setText(item.getTitle());
        binding.textPrice.setText(String.format("%d원", item.getPrice()));
        binding.textDescription.setText(Html.fromHtml(item.getDescription()));

        Glide.with(this).load(item.getImage()).into(binding.imageBook);
    }

}





//    public void save() {
//        // TODO HERE
//        try {
//            FileOutputStream fos = openFileOutput("memo.txt", Context.MODE_PRIVATE);
//            fos.write(binding.editMemo.getText().toString().getBytes(StandardCharsets.UTF_8));
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void load() {
//        String jsonString = FileUtils.readFromAssets(this, "abook.json");
//        Log.i("JSON", jsonString);
//
//        Gson gson = new Gson();
//        BookModel book = gson.fromJson(jsonString, BookModel.class);
//        String title = book.getTitle();
//        binding.editMemo.setText(title);
//    }
//
//
//
//    public static String readFile(Context context, String filename) throws FileNotFoundException {
//        FileInputStream fis = context.openFileInput(filename);
//        String contents = "";
//        InputStreamReader inputStreamReader =
//                new InputStreamReader(fis, StandardCharsets.UTF_8);
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
//            String line = reader.readLine();
//            while (line != null) {
//                stringBuilder.append(line).append('\n');
//                line = reader.readLine();
//            }
//        } catch (IOException e) {
//            // error occurred while opening a raw file for reading.
//        } finally {
//            contents = stringBuilder.toString().trim();
//        }
//        return contents;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        load();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        save();
//    }
//
//}
//
//
//    private void writeFile(String filename, String data) {
//        try {
//            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
//            fos.write(data.getBytes(StandardCharsets.UTF_8));
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void save() {
//        String contents = binding.editMemo.getText().toString();
//        FileUtils.writeFile(this, "memo.txt", contents);
//    }
//
//    private void load(String filename) {
//        try {
//            String loadedContents = FileUtils.readFile(filename);
//            binding.editMemo.setText(loadedContents);
//        } catch (FileNotFoundException e ) {
//        }
//
//    }
//
//    private String readFile(String filename) throws FileNotFoundException {
//        FileInputStream fis = openFileInput(filename);
//
//        InputStreamReader inputStreamReader =
//                new InputStreamReader(fis, StandardCharsets.UTF_8);
//
//        StringBuilder stringBuilder = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
//            String line = reader.readLine();
//            while (line != null) {
//                stringBuilder.append(line).append('\n');
//                line = reader.readLine();
//            }
//        } catch (IOException e) {
//            // error occurred while opening a raw file for reading.
//        }
//        return StringBuilder.toString().trim();
//    }
//
//    public static void writeFile(Context context, String filename, String data){
//        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
//            fos.write(data.getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
