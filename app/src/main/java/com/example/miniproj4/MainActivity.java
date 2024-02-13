package com.example.miniproj4;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // "LOAD IMAGE" button
        Button gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the "LOAD IMAGE" button is clicked, open the gallery for image selection
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        // // Initialize TextViews for displaying random string and number
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            // Get the selected image URI from the gallery
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.imageView);

            // Convert URI to Bitmap
            Bitmap bitmap = getBitmapFromUri(selectedImage);

            // Display the Bitmap in ImageView
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);

                // Update TextViews with random string and number
                updateRandomValues();
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // Use BitmapFactory to decode the URI into a Bitmap
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateRandomValues() {
        // Randomly select a string from the list and display it in the first TextView
        String randomString = getRandomString();
        text1.setText(randomString);

        // Randomly select a number from the list and display it in the second TextView
        int randomNumber = getRandomNumber();
        text2.setText(String.valueOf(randomNumber));
    }

    private String getRandomString() {
        // List of strings to choose from
        List<String> stringList = new ArrayList<>();
        stringList.add("Rose");
        stringList.add("Orchid");
        stringList.add("Sunflower");
        stringList.add("Tulip");
        stringList.add("Daisy");

        // Randomly select a string from the list
        Random random = new Random();
        int randomIndex = random.nextInt(stringList.size());
        return stringList.get(randomIndex);
    }

    private int getRandomNumber() {
        // List of numbers to choose from
        List<Integer> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
        numberList.add(4);
        numberList.add(5);

        // Randomly select a number from the list
        Random random = new Random();
        int randomIndex = random.nextInt(numberList.size());
        return numberList.get(randomIndex);
    }
}

