package com.example.w6_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);



        if (sharedPreferences.contains("imagePreference")) {
            String imageString = sharedPreferences.getString("imagePreference", "");
            byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            image.setImageBitmap(decodedImage);
        }
        if (sharedPreferences.contains("editText")) {
            String str = sharedPreferences.getString("editText", "");
            editText.setText(str);
        }

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get img from values res -> randomise -> setNewImg
                TypedArray imgs = getResources().obtainTypedArray(R.array.driver_images);
                Random rand = new Random();
                int rndInt = rand.nextInt(imgs.length());
                int resID = imgs.getResourceId(rndInt, 0);
                image.setImageResource(resID);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bb = bos.toByteArray();
        String imageString = Base64.encodeToString(bb,Base64.DEFAULT);

        myEdit.putString("imagePreference", imageString);
        myEdit.putString("editText", editText.getText().toString());
        myEdit.apply();

        // resources used for help:
        // https://stackoverflow.com/questions/7315498/how-to-store-and-retrieve-bitmap-in-sharedpreferences-in-android
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bb = bos.toByteArray();
        String imageString = Base64.encodeToString(bb,Base64.DEFAULT);

        myEdit.putString("imagePreference", imageString);
        myEdit.putString("editText", editText.getText().toString());
        myEdit.apply();
    }
}